package com.mario.gamermvvmapp.presentation.screens.my_post.editarPost

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.use_cases.posts.PostsUseCase
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostViewModel
import com.mario.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.mario.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditarMyPostViewModel @Inject constructor(@ApplicationContext private val context: Context,
            private val savedStateHandle: SavedStateHandle,
            private val
            postsUseCase: PostsUseCase): ViewModel() {

    var state by mutableStateOf(EditState())

    // ARGUMENTS
    val data = savedStateHandle.get<String>("post")
    val post = data?.let { Post.fromJson(it) }
    var _updateFlow by mutableStateOf<Response<Boolean>?>(null)

    val radiOptions = listOf(
        NewPostViewModel.PrivacyRadioButton("publico", R.drawable.public_relation),
        NewPostViewModel.PrivacyRadioButton("solo amigos", R.drawable.friends),
        NewPostViewModel.PrivacyRadioButton("privado", R.drawable.privacy)
    )

    //imagen url
    var  file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()
    var userImageShow: String by mutableStateOf("")

    init {
        state = state.copy(
            id = post?.id ?:"",
            name = post?.name ?:"",
            description = post?.description ?:"",
            image = post?.image ?:"",
            privacy = post?.privacy ?:"",
            idUser = post?.idUser ?:""
        )
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
            userImageShow =  result.toString()

        }
    }

    fun onUpdatePost(){


        if(state.name!="" && state.description!="" && state.privacy!="" && state.image!="") {
            val posts = Post(
                name = state.name,
                description = state.description,
                privacy = state.privacy,
                idUser = state.idUser,
                image=state.image,
                id = state.id
            )
            updatePost(posts)
        }else{
            Toast.makeText(context,"Tiene algun campo vacio", Toast.LENGTH_LONG).show()
        }
    }


    fun updatePost(post: Post)= viewModelScope.launch{
        Log.d("NEGRYYYYY",""+post.name)
        Log.d("NEGRYYYYY",""+post.description)
        Log.d("NEGRYYYYY",""+post.privacy)
        Log.d("NEGRYYYYY",""+post.id)
        _updateFlow = Response.Loading
          val resul= postsUseCase.updatePost(post)
        //resultado que nos devolvio esa peticion
         _updateFlow = resul
    }


    //METODO PARA TOMAR LA FOTOGRAFIA
    fun takePhoto()= viewModelScope.launch{
        val resul = resultingActivityHandler.takePicturePreview()
        if (resul!=null) {
            userImageShow = ComposeFileProvider.getPathFromBitmap(context, resul)
            file = File(userImageShow)
        }
    }


    data class PrivacyRadioButton(
        var nombre:String,
        var image: Int
    )

}



