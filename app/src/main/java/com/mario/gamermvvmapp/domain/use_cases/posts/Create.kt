package com.mario.gamermvvmapp.domain.use_cases.posts
import com.mario.gamermvvmapp.domain.repository.PostsRepository
import com.mario.gamermvvmapp.domain.model.Post
import java.io.File
import javax.inject.Inject


class CreatePost @Inject constructor(private val repository: PostsRepository){

    suspend operator fun invoke(post: Post, file: File) = repository.create(post, file)
}