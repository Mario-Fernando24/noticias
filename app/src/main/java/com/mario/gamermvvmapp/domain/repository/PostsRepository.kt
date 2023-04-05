package com.mario.gamermvvmapp.domain.repository

import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostsRepository {

    suspend fun create(post:Post, file: File): Response<Boolean>
   //Flow para obtener la data en tiempo real
    //obtenemos una list de post (publicaciones)
    fun getPosts(): Flow<Response<List<Post>>>

    //obtener todos los post
    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>

    //eliminar post
    suspend fun delete(idPost: String): Response<Boolean>

}