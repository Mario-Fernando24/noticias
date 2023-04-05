package com.mario.gamermvvmapp.data.repository

import android.annotation.SuppressLint
import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.mario.gamermvvmapp.core.Constant
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImp @Inject constructor(
    @Named(Constant.POSTS_COLECTION) private val postsRef: CollectionReference,
    @Named(Constant.USERS_COLECTION) private val usersRef: CollectionReference,
    @Named(Constant.POSTS_COLECTION) private val storagePostsRef: StorageReference
): PostsRepository {

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {

            //IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
             post.image=url.toString()

            //DATA
            postsRef.add(post).await()
            Response.Success(true)

        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow{
        val snapshotListener = postsRef.addSnapshotListener{
            snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {

                val postsResponse = if(snapshot!=null){
                    val posts = snapshot.toObjects(Post::class.java)
                    //optimizamos para que no haga muchas peticiones si no que agrupe los id que sin repetirse

                    val idUserArray = ArrayList<String>()

                    posts.forEach { it ->
                       idUserArray.add(it.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList() //ELEMENTOS SIN REPETIR

                    idUserList.map { id->
                        async {
                          val user= usersRef.document(id).get().await().toObject(User::class.java)!!
                            posts.forEach { post->
                                if(post.idUser == id){
                                    post.user = user
                                }
                            }
                        }
                    }.forEach{
                        it.await( )
                    }

                    Response.Success(posts)

                }else{
                    Response.Failure(e)
                }
                trySend(postsResponse)

            }


        }
        //dejamos de escuchar cuando no sea necesario
        awaitClose {
            snapshotListener.remove()
        }
        TODO("Not yet implemented")
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow{

        val snapshotListener = postsRef.whereEqualTo("idUser", idUser).addSnapshotListener{
                snapshot, e ->

                val postsResponse = if(snapshot!=null){

                    val posts = snapshot.toObjects(Post::class.java)

                    snapshot.documents.forEachIndexed { index, docum ->
                        posts[index].id = docum.id
                    }

                    Response.Success(posts)

                }else{
                    Response.Failure(e)
                }
                trySend(postsResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }



    }

    override suspend fun delete(idPost: String): Response<Boolean> {

        return try {

            postsRef.document(idPost).delete().await()

            Response.Success(true)
        } catch (e: Exception) {
        e.printStackTrace()
        Response.Failure(e)
    }
    }

}