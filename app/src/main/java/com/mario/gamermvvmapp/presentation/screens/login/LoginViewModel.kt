package com.mario.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//injeccion de dependencia
@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid:MutableState<Boolean> = mutableStateOf(false)
    var EmailErrorMsg:MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid:MutableState<Boolean> = mutableStateOf(false)
    var PasswordErrorMsg:MutableState<String> = mutableStateOf("")

    var isEnableLoginButton = false

    fun validateEmail(){

        //SABER SI ES UN EMAIL VALIDO
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value=true
            EmailErrorMsg.value=""
        }else{
            isEmailValid.value=false
            EmailErrorMsg.value="El email no es valido"
        }
        enableLoginButton()
    }


    fun validatePassword(){

        //SABER SI ES UN EMAIL VALIDO
        if(password.value.length>=8){
            isPasswordValid.value=true
            PasswordErrorMsg.value=""
        }else{
            isPasswordValid.value=false
            PasswordErrorMsg.value="Almenos 8 caracteres"
        }
        enableLoginButton()
    }

    fun enableLoginButton(){
        isEnableLoginButton = isEmailValid.value && isPasswordValid.value
    }



    }