package com.mario.gamermvvmapp.presentation.screens.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mario.gamermvvmapp.presentation.ui.utils.isLettersOrDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    var userName: MutableState<String> = mutableStateOf("")
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrorMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var EmailErrorMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var PasswordErrorMsg: MutableState<String> = mutableStateOf("")

    var confirPassword: MutableState<String> = mutableStateOf("")
    var isconfirPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confirPasswordErrorMsg: MutableState<String> = mutableStateOf("")

    var isEnableRegisterButton = false

    fun validateUserName(){

        if(userName.value.length>=3){
            isUserNameValid.value=true
            userNameErrorMsg.value=""
        }else{
            isUserNameValid.value=false
            userNameErrorMsg.value="El usuario no es valido"
        }
        enableRegisterButton()
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
        enableRegisterButton()
    }


    fun validatePassword(){
        Log.d("marioferokoko",""+  password.value.toString())
        Log.d("mariofer",""+isLettersOrDigits(password.value))
      if(isLettersOrDigits(password.value)) {

          //SABER SI ES UN EMAIL VALIDO
          if (password.value.length >= 8) {
              isPasswordValid.value = true
              PasswordErrorMsg.value = ""
          } else {
              isPasswordValid.value = false
              PasswordErrorMsg.value = "Almenos 8 caracteres"
          }
          validateconfirmPassword()
          enableRegisterButton()
      }else{
          isPasswordValid.value = false
          PasswordErrorMsg.value = "Su contraseña debe ser alfanumerica"

      }

    }

    fun validateconfirmPassword(){

        if(password.value==confirPassword.value){
            isconfirPasswordValid.value=true
            confirPasswordErrorMsg.value=""
        }else{
            isconfirPasswordValid.value=false
            confirPasswordErrorMsg.value="La contraseña no coinciden"
        }
        enableRegisterButton()

    }

    fun enableRegisterButton(){
        isEnableRegisterButton = isUserNameValid.value && isEmailValid.value && isPasswordValid.value && isconfirPasswordValid.value
    }


}
