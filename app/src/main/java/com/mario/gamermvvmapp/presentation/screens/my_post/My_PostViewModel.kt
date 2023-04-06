package com.mario.gamermvvmapp.presentation.screens.my_post

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mario.gamermvvmapp.domain.use_cases.posts.PostsUseCase
import com.mario.gamermvvmapp.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class My_PostViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val postsUseCase: PostsUseCase): ViewModel() {

    var mypostsResponse by mutableStateOf<Response<List<Post>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)

    init {
        getPost()
    }

    fun delete(idPost:String) = viewModelScope.launch {

        deleteResponse = Response.Loading
        val resul= postsUseCase.deletePost(idPost)
        deleteResponse = resul

    }


    fun getPost() = viewModelScope.launch {

        mypostsResponse = Response.Loading

        postsUseCase.getPostById(authUseCases.getCurrentUserg()!!.uid).collect() { resp ->
            mypostsResponse = resp
        }

    }
}


