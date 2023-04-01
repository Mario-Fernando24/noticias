package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.repository.PostsRepository
import javax.inject.Inject

class GetPost @Inject constructor(private  val repository: PostsRepository){
    
    operator fun invoke() = repository.getPosts()
}