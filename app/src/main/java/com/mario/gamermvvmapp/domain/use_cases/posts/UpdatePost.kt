package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.repository.PostsRepository
import javax.inject.Inject

class UpdatePost @Inject constructor(private val repository: PostsRepository) {

    suspend operator fun invoke(post: Post) = repository.updatePost(post)
}