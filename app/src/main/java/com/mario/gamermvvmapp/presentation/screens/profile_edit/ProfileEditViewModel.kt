package com.mario.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import javax.inject.Inject

class ProfileEditViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel()  {

    var userName: String by  mutableStateOf("")
    var isUserNameValid: Boolean by  mutableStateOf(false)
    var userNameErrorMsg: String by  mutableStateOf("")
    var estado: String by  mutableStateOf("")

    val dataa= savedStateHandle.get<String>("user")
    val userr = User.fromJson(dataa!!)

    init {
        userName=userr.username
    }


    fun validateUserName(){

        if(userName.length>=3){
            isUserNameValid=true
            userNameErrorMsg=""
        }else{
            isUserNameValid=false
            userNameErrorMsg="El usuario no es valido"
        }
      //  enableRegisterButton()
    }


}