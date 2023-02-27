package com.mario.gamermvvmapp.domain.use_cases.auth

import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Register @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.register(user)
}

