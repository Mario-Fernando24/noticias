package com.mario.gamermvvmapp.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.posts.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(
    private val postsUseCase: PostsUseCase,
    private val authUseCases: AuthUseCases
) : ViewModel(){

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var likeResponse by mutableStateOf<Response<Boolean>?>(null)
    var deleteLikeResponse by mutableStateOf<Response<Boolean>?>(null)
    var currentUser=authUseCases.getCurrentUserg()

    init {
        getPost()
    }

    fun like(idPost:String, idUser:String) = viewModelScope.launch {

        likeResponse = Response.Loading
        val res = postsUseCase.addLike(idPost, idUser)
        likeResponse = res
    }

    fun deleteLike(idPost:String, idUser:String) = viewModelScope.launch {

        deleteLikeResponse = Response.Loading
        val res = postsUseCase.deleteLike(idPost, idUser)
        deleteLikeResponse = res
    }
    fun getPost() = viewModelScope.launch {

         postsResponse = Response.Loading

         postsUseCase.getPost().collect(){resp->
             postsResponse=resp
         }

    }


}
