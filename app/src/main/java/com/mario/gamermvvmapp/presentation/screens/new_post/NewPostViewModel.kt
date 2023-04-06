package com.mario.gamermvvmapp.presentation.screens.new_post

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.posts.PostsUseCase
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import com.mario.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.mario.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context, private val postsUseCase: PostsUseCase,
    private val useCases: UsersUseCase, private val authUseCases: AuthUseCases,
) : ViewModel(){

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
    var userData by mutableStateOf(User())
    var savePostResponse by mutableStateOf<Response<Boolean>?>(null)

    init {
        getUserId()
    }

    private fun getUserId() = viewModelScope.launch{
        useCases.getUserById(authUseCases.getCurrentUserg()!!.uid).collect(){ user->
            userData=user
        }
    }

    fun onNameInput(name: String){
        state =state.copy(name=name)
    }

    fun onDescriptionInput(description: String){
        state =state.copy(description = description)
    }

    fun onPrivacyInput(privacy: String){
        state =state.copy(privacy=privacy)
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

        if(state.name!="" && state.description!="" && state.privacy!="" && state.image!="") {
            val posts = Post(
                name = state.name,
                description = state.description,
                privacy = state.privacy,
                idUser = userData.id
            )
            savePost(posts)
        }else{
            Toast.makeText(context,"Tiene algun campo vacio", Toast.LENGTH_LONG).show()
        }
    }

    fun clearForm(){
        state= state.copy(
            name = "",
            description = "",
            privacy = ""
        )

        savePostResponse = null
    }


    fun savePost(post: Post) = viewModelScope.launch {

        Log.d("MARIOFER","")
        Log.d("MARIOFER","")
        Log.d("MARIOFER",""+post.id)
        Log.d("MARIOFER","")

            savePostResponse = Response.Loading
            val result = postsUseCase.create(post, file!!)
            savePostResponse = result
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

    data class PrivacyRadioButton(
        var nombre:String,
        var image: Int
    )

}

