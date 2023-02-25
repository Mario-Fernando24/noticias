package com.mario.gamermvvmapp.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.mario.gamermvvmapp.domain.model.Response


//definir metodos y variables
interface AuthRepository {

    val currentUser:FirebaseUser?
   // la respuesta que nos devuelve al momento de logearno es FirebaseUser
    suspend fun login(email:String , password: String): Response<FirebaseUser>

    fun  logout()
}