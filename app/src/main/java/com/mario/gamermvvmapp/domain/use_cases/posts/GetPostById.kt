package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostById @Inject constructor(private  val repository: PostsRepository){
     operator fun invoke(id:String) = repository.getPostsByUserId(id)

}