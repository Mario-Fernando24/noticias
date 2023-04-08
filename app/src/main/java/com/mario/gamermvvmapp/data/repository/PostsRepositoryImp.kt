package com.mario.gamermvvmapp.data.repository

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
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

            val document: DocumentReference = postsRef.document()
            post.id=document.id
            document.set(post).await()

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
                            val user= usersRef?.document(id).get().await().toObject(User::class.java)!!
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

    override suspend fun updatePost(post: Post): Response<Boolean> {

        return try {

            val map:MutableMap<String, Any> = HashMap()

                map["id"] = post.id
                map["name"] = post.name
                map["description"] = post.description
                map["privacy"] = post.privacy
                map["image"] = post.image
                map["idUser"] = post.idUser

            postsRef.document(post.id).update(map).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override suspend fun updateImagePost(file: File): Response<String> {

            return try {
                val fromFile =Uri.fromFile(file)
                val ref = storagePostsRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()

                return Response.Success(url.toString())

            }catch (e: Exception){
                e.printStackTrace()
                Response.Failure(e)
            }
        }

    override suspend fun likes(idPost: String, idUser: String): Response<Boolean> {
        return try {

            postsRef.document(idPost).update("likes",FieldValue.arrayUnion(idUser)).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean> {

        return try {

            postsRef.document(idPost).update("likes",FieldValue.arrayRemove(idUser)).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

}