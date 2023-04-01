package com.mario.gamermvvmapp.domain.use_cases.auth

import com.mario.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository) {

    //cuando llamamos esta funcion login, la va a disparar automaticamente
   //dispara con invoke
    suspend operator fun invoke(email: String, password: String) = repository.login(email,password)
}