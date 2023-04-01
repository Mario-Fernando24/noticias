package com.mario.gamermvvmapp.data.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.CollectionReference

import com.google.firebase.storage.StorageReference
import com.mario.gamermvvmapp.core.Constant
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImp @Inject constructor(
    @Named(Constant.USERS_COLECTION) private val userRef: CollectionReference,
    @Named(Constant.USERS_COLECTION) private val storageUsersRef: StorageReference
    ): UsersRepository {

    //manera antigua
    // val firestore=Firebase.firestore
    // val userRef=firestore.collection("Users")

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            userRef.document(user.id).set(user).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun updateUser(user: User): Response<Boolean> {

        return try {

            val map:MutableMap<String, Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image
            map["city"] = user.city
           Log.d("juan","juan${user.username}")

            userRef.document(user.id).update(map).await()
            Response.Success(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    override fun getUsersById(id: String): Flow<User> = callbackFlow {

        val snapshotListener = userRef.document(id).addSnapshotListener{snapshot,e->


            val user = snapshot?.toObject(User::class.java) ?: User()
            //emite la información cuando sea requerida

            trySend(user)
        }

        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun saveImage(file: File): Response<String> {

         return try {
           val fromFile =Uri.fromFile(file)
           val ref = storageUsersRef.child(file.name)
           val uploadTask = ref.putFile(fromFile).await()
           val url = ref.downloadUrl.await()

             return Response.Success(url.toString())

        }catch (e: Exception){
             e.printStackTrace()
             Response.Failure(e)
         }
    }


}