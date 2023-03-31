package com.mario.gamermvvmapp.di.repository

import com.google.firebase.auth.FirebaseUser
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User


//definir metodos y variables
interface AuthRepository {

    val currentUser:FirebaseUser?
   // la respuesta que nos devuelve al momento de logearno es FirebaseUser
    suspend fun login(email:String , password: String): Response<FirebaseUser>
    suspend fun register(user: User): Response<FirebaseUser>



    fun  logout()
}