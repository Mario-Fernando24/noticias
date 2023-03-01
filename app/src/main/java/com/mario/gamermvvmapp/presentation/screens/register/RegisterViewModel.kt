package com.mario.gamermvvmapp.presentation.screens.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.*
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import com.mario.gamermvvmapp.presentation.ui.utils.isLettersOrDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCases: AuthUseCases, val  usersUseCase: UsersUseCase): ViewModel() {

    var userName: String by  mutableStateOf("")
    var isUserNameValid: Boolean by  mutableStateOf(false)
    var userNameErrorMsg: String by  mutableStateOf("")

    var email: String by  mutableStateOf("")
    var isEmailValid: Boolean by  mutableStateOf(false)
    var EmailErrorMsg: String by  mutableStateOf("")

    var password: String by  mutableStateOf("")
    var isPasswordValid: Boolean by  mutableStateOf(false)
    var PasswordErrorMsg:String by  mutableStateOf("")

    var confirPassword: String by  mutableStateOf("")
    var isconfirPasswordValid: Boolean by  mutableStateOf(false)
    var confirPasswordErrorMsg: String by  mutableStateOf("")

    var isEnableRegisterButton = false


    var _registerFlow by mutableStateOf<Response<FirebaseUser>?>(null)

    var user = User()

    fun onRegister(){
        user.username=userName
        user.email=email
        user.password=password


        register(user)
    }

    fun register(user: User)= viewModelScope.launch{

        _registerFlow = Response.Loading
        val resul= authUseCases.register(user)
        //resultado que nos devolvio esa peticion
        _registerFlow = resul
    }

    fun createUser() = viewModelScope.launch{
         user.id= authUseCases.getCurrentUserg()!!.uid
         usersUseCase.create(user)
    }

    fun validateUserName(){

        if(userName.length>=3){
            isUserNameValid=true
            userNameErrorMsg=""
        }else{
            isUserNameValid=false
            userNameErrorMsg="El usuario no es valido"
        }
        enableRegisterButton()
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
        enableRegisterButton()
    }


    fun validatePassword(){
        Log.d("marioferokoko",""+  password.toString())
        Log.d("mariofer",""+isLettersOrDigits(password))
      if(isLettersOrDigits(password)) {

          //SABER SI ES UN EMAIL VALIDO
          if (password.length >= 8) {
              isPasswordValid = true
              PasswordErrorMsg = ""
          } else {
              isPasswordValid = false
              PasswordErrorMsg = "Almenos 8 caracteres"
          }
          validateconfirmPassword()
          enableRegisterButton()
      }else{
          isPasswordValid = false
          PasswordErrorMsg = "Su contraseña debe ser alfanumerica"

      }

    }

    fun validateconfirmPassword(){

        if(password==confirPassword){
            isconfirPasswordValid=true
            confirPasswordErrorMsg=""
        }else{
            isconfirPasswordValid=false
            confirPasswordErrorMsg="La contraseña no coinciden"
        }
        enableRegisterButton()

    }

    fun enableRegisterButton(){
        isEnableRegisterButton = isUserNameValid && isEmailValid && isPasswordValid && isconfirPasswordValid
    }


}
