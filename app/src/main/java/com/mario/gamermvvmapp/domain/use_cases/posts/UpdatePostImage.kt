package com.mario.gamermvvmapp.domain.use_cases.posts

import com.mario.gamermvvmapp.domain.repository.PostsRepository
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import java.io.File
import javax.inject.Inject

class UpdatePostImage  @Inject constructor(private val repository: PostsRepository){

    suspend operator fun invoke(file: File) = repository.updateImagePost(file)

}