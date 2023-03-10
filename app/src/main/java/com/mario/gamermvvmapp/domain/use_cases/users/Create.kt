package com.mario.gamermvvmapp.domain.use_cases.users

import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository){

   suspend operator fun invoke(user: User) = repository.create(user)
}