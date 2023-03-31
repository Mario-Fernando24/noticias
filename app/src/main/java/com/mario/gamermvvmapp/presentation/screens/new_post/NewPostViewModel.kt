package com.mario.gamermvvmapp.presentation.screens.new_post

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.mario.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) :ViewModel(){

    var state by mutableStateOf(NewPostState())
    var  file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    // NAME VALIDATIONS
    var isNameValid by mutableStateOf(false)
        private set

    var nameErrMsg by mutableStateOf("")
        private set

    // NAME VALIDATIONS
    var isDescriptionValid by mutableStateOf(false)
        private set

    var descriptionErrMsg by mutableStateOf("")
        private set

    val radiOptions = listOf(
        PrivacyRadioButton("publico", R.drawable.public_relation),
        PrivacyRadioButton("solo amigos", R.drawable.friends),
        PrivacyRadioButton("privado", R.drawable.privacy)
    )

    fun onNameInput(name: String){
        state =state.copy(name=name)
    }

    fun onDescriptionInput(description: String){
        state =state.copy(description = description)
    }

    fun onPrivacyInput(privacy: String){
        state =state.copy(privacy=privacy)
    }

    fun onImageInput(image: String){
        state =state.copy(image = image)
    }

    fun pickImage()= viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result!=null) {

            file = ComposeFileProvider.createFileFromUri(context, result)
            state=state.copy(image = result.toString())
        }
    }


    //METODO PARA TOMAR LA FOTOGRAFIA
    fun takePhoto()= viewModelScope.launch{
        val resul = resultingActivityHandler.takePicturePreview()
        if (resul!=null) {
            state =state.copy(image =  ComposeFileProvider.getPathFromBitmap(context, resul))
            file = File(state.image)
        }
    }

    fun onNewPost(){
        Log.d("MARIO","Nombre "+state.name);
        Log.d("MARIO","Descripción"+state.description);
        Log.d("MARIO","Privacidad "+state.privacy);
        Log.d("MARIO","Imagen "+state.image);
    }



    fun validateName(){

        if(state.name.isNotEmpty()){
            isNameValid=true
            nameErrMsg=""
        }else{
            isNameValid=false
            nameErrMsg="La publicacion debe tener un titulo"
        }
      //  enableLoginButton()
    }

    fun validateDescription(){

        if(state.description.isNotEmpty()){
                isDescriptionValid=true
                descriptionErrMsg=""
            }else{
                isDescriptionValid=false
                descriptionErrMsg="Por favor agregar una descripción"
            }
    }



}

data class PrivacyRadioButton(
    var nombre:String,
    var image: Int
)
