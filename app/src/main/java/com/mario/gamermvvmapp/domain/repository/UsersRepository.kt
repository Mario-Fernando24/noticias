package com.mario.gamermvvmapp.domain.repository

import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

//se encarga de definir los metodos de los usuario crear, editar, eliminar and listar
interface UsersRepository {

    //para crear un usuario le enviamos el modelo y nos retorno una variable de tipo booleana
   suspend fun create(user: User):Response<Boolean>
   suspend fun updateUser(user: User): Response<Boolean>
   fun getUsersById(id: String): Flow<User>

   //save img firestorage send img of type file y nos devuelve la url que se almaceno
   suspend fun saveImage(file:File): Response<String>
}