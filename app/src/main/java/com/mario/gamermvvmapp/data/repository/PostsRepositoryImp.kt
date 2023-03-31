package com.mario.gamermvvmapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.di.repository.PostsRepository
import com.mario.gamermvvmapp.di.repository.UsersRepository
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val postsRef: CollectionReference,
    private val storagePostsRef: StorageReference
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

}