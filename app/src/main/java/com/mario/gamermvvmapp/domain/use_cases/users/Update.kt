package com.mario.gamermvvmapp.domain.use_cases.users

import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke(user: User) = repository.updateUser(user)

}
