package com.mario.gamermvvmapp.presentation.screens.profile_edit

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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

    var userCity: String by mutableStateOf("")
    var isUserCityValid: Boolean by mutableStateOf(false)
    var userCityErrorMsq: String by mutableStateOf("")

    var estado: String by  mutableStateOf("")



    val dataa= savedStateHandle.get<String>("user")
    val userr = User.fromJson(dataa!!)

    var _updateFlow by mutableStateOf<Response<Boolean>?>(null)

    var imageUrl by mutableStateOf<Uri?>(null)
    var hasImage by mutableStateOf<Boolean>(false)
    init {
        userCity=userr.city
        userName=userr.username
    }

    fun onGalleryResult(uri:Uri?){
        hasImage = uri!=null // true false
        imageUrl=uri
    }

    fun onCameraResult(result:Boolean){
       hasImage = result
    }

    fun onUpdate(){
        val use=User(
            id = userr.id,
            username=userName,
            email= userr.email,
            password= userr.password,
            image= userr.image,
            city = userCity
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
            userNameErrorMsg="campo usuario vacio"
        }
      //  enableRegisterButton()
    }

    fun validateUserCity(){

        if(userCity.length>=2){
            isUserCityValid=true
            userCityErrorMsq=""
        }else{
            isUserCityValid=false
            userCityErrorMsq="campo ciudad vacio"
        }
    }

}