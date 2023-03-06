package com.mario.gamermvvmapp.presentation.screens.profile_edit

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mario.gamermvvmapp.domain.model.Response

import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileEditViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle, private val
    usersUseCase: UsersUseCase): ViewModel() {

    var userName: String by  mutableStateOf("")
    var isUserNameValid: Boolean by  mutableStateOf(false)
    var userNameErrorMsg: String by  mutableStateOf("")
    var estado: String by  mutableStateOf("")

    val dataa= savedStateHandle.get<String>("user")
    val userr = User.fromJson(dataa!!)

    var _updateFlow by mutableStateOf<Response<Boolean>?>(null)


    init {
        userName=userr.username
    }

    fun onUpdate(){
        val use=User(
            id = userr.id,
            username=userName,
            email= userr.email,
            password= userr.password,
            image= userr.image
        )
       update(use)
    }

    fun update(user: User)= viewModelScope.launch{

        _updateFlow = Response.Loading
        val resul= usersUseCase.update(user)
        //resultado que nos devolvio esa peticion
        _updateFlow = resul
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