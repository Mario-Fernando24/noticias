package com.mario.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//injeccion de dependencia
@HiltViewModel
//tengo acceso a todos los metodos por medio del constructor private val authUseCases: AuthUseCases)
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    var email:String by  mutableStateOf("")
    var isEmailValid:Boolean by  mutableStateOf(false)
    var EmailErrorMsg:String by  mutableStateOf("")


    var password:String by  mutableStateOf("")
    var isPasswordValid:Boolean by  mutableStateOf(false)
    var PasswordErrorMsg:String by  mutableStateOf("")

    var isEnableLoginButton = false

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
     val currenUserr=authUseCases.getCurrentUserg()
    //lo primero que ejecuta
    init {
       //preguntar si el currentUserr "usuario" llega vacio
        //session iniciada
        if(currenUserr!=null){
            loginResponse = Response.Success(currenUserr)
        }
    }

    //cuando es una funcion con currutina utilizamos
    fun login() = viewModelScope.launch {
        loginResponse= Response.Loading
        val resul= authUseCases.login(email, password)
        loginResponse= resul
    }
    fun validateEmail(){

        //SABER SI ES UN EMAIL VALIDO
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isEmailValid=true
            EmailErrorMsg=""
        }else{
            isEmailValid=false
            EmailErrorMsg="El email no es valido"
        }
        enableLoginButton()
    }


    fun validatePassword(){

        //SABER SI ES UN EMAIL VALIDO
        if(password.length>=8){
            isPasswordValid=true
            PasswordErrorMsg=""
        }else{
            isPasswordValid=false
            PasswordErrorMsg="Almenos 8 caracteres"
        }
        enableLoginButton()
    }

    fun enableLoginButton(){
        isEnableLoginButton = isEmailValid && isPasswordValid
    }

    }