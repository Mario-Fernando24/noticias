package com.mario.gamermvvmapp.di.repository

import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import java.io.File

interface PostsRepository {

    suspend fun create(post:Post, file: File): Response<Boolean>

}