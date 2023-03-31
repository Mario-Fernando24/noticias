package com.mario.gamermvvmapp.domain.use_cases.users

import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.di.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository){
    suspend operator fun invoke(id:String) = repository.getUsersById(id)
}