package com.mario.gamermvvmapp.presentation.screens.profile_edit

import android.content.Context
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
import com.mario.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.mario.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class ProfileEditViewModel  @Inject constructor(
    private val savedStateHandle: SavedStateHandle, private val
    usersUseCase: UsersUseCase,
    @ApplicationContext private val context: Context
    ): ViewModel() {

    var userName: String by  mutableStateOf("")
    var isUserNameValid: Boolean by  mutableStateOf(false)
    var userNameErrorMsg: String by  mutableStateOf("")

    var userCity: String by mutableStateOf("")
    var isUserCityValid: Boolean by mutableStateOf(false)
    var userCityErrorMsq: String by mutableStateOf("")

    var userImageShow: String by mutableStateOf("")
    var estado: String by  mutableStateOf("")



    val dataa= savedStateHandle.get<String>("user")
    val userr = User.fromJson(dataa!!)

    var _updateFlow by mutableStateOf<Response<Boolean>?>(null)
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
    var imageUri by mutableStateOf("")
    //imagen url
    var  file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()
    init {
        userCity=userr.city
        userName=userr.username
        userImageShow = userr.image

    }

    //agregar imagen en firestorage
    fun saveImage() = viewModelScope.launch {
        if(file!=null) {
            saveImageResponse = Response.Loading
            val result = usersUseCase.saveImage(file!!)
            saveImageResponse = result
        }
    }

    // METODO PARA TOMAR FOTO DE LA GALERIA DEL DISPOSITIVO
    fun pickImage()= viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result!=null) {

            file = ComposeFileProvider.createFileFromUri(context, result)
            userImageShow =  result.toString()

        }
    }


    //METODO PARA TOMAR LA FOTOGRAFIA
     fun takePhoto()= viewModelScope.launch{
        val resul = resultingActivityHandler.takePicturePreview()
          if (resul!=null) {
              userImageShow = ComposeFileProvider.getPathFromBitmap(context, resul)
              file = File(userImageShow)
          }
     }

    fun onUpdate(url:String){

        val use=User(
            id = userr.id,
            username=userName,
            email= userr.email,
            password= userr.password,
            image= url,
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