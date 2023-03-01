package com.mario.gamermvvmapp.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//para poder inyectar dependencias HiltViewModel
@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases,private val useCases: UsersUseCase) :ViewModel(){


    var userData by mutableStateOf(User())
    //que esta información no va hacer cambiada desde otra clase
    private set

    init {
        getUserId()
    }

    private fun getUserId() = viewModelScope.launch{
        useCases.getUserById(authUseCases.getCurrentUserg()!!.uid).collect(){ user->
            userData=user
        }
    }

    fun logout(){
        authUseCases.logout()
    }
}