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

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid:MutableState<Boolean> = mutableStateOf(false)
    var EmailErrorMsg:MutableState<String> = mutableStateOf("")


    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid:MutableState<Boolean> = mutableStateOf(false)
    var PasswordErrorMsg:MutableState<String> = mutableStateOf("")

    var isEnableLoginButton = false

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> =_loginFlow

     val currenUserr=authUseCases.getCurrentUserg()
    //lo primero que ejecuta
    init {
       //preguntar si el currentUserr "usuario" llega vacio
        //session iniciada
        if(currenUserr!=null){
            _loginFlow.value =Response.Success(currenUserr)
        }
    }

    //cuando es una funcion con currutina utilizamos
    fun login() = viewModelScope.launch {
        _loginFlow.value= Response.Loading
        val resul= authUseCases.login(email.value, password.value)
        _loginFlow.value= resul
    }
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