package com.mario.gamermvvmapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//para poder inyectar dependencias HiltViewModel
@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases) :ViewModel(){


    fun logout(){
        authUseCases.logout()
    }
}