package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.repository.PostsRepository
import javax.inject.Inject

class DeleteLike @Inject constructor(private  val repository: PostsRepository){

    suspend operator fun invoke(idPost:String, idUser: String) = repository.deleteLike(idPost, idUser)


}