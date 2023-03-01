package com.mario.gamermvvmapp.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(private val userRef: CollectionReference): UsersRepository {

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


}