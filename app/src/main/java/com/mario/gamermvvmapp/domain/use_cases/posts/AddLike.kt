package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.repository.PostsRepository
import javax.inject.Inject

class AddLike @Inject constructor(private  val repository: PostsRepository){

    suspend operator fun invoke(idPost:String, idUser: String) = repository.likes(idPost, idUser)


}