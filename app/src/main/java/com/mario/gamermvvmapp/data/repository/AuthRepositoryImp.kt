package com.mario.gamermvvmapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
//aqui tenemos la implementacion de los metodos que hicimos en la capa de dominio
//va a implementar la interfaz del dominio y le inyectamos la dependencias @Inject constructor(private val firebaseAuth: FirebaseAuth)
class AuthRepositoryImp  @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository{

    //val firebaseAut= FirebaseAuth.getInstance()
    //nos devuelve el usuario de sesion siempre que exista
    override val currentUser: FirebaseUser?get() = firebaseAuth.currentUser

     override suspend fun login(email: String, password: String): Response<FirebaseUser> {

        return try {
            //hacemos la consulta a firebase y esperamos con las courutinas
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)
        }catch (e: Exception){
              e.printStackTrace()
              Response.Failure(e)
        }
    }
}