package com.mario.gamermvvmapp.domain.use_cases.auth

import com.mario.gamermvvmapp.di.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository){

    //nos devuelve el usuario actual logiado
    operator fun invoke()= repository.currentUser

}