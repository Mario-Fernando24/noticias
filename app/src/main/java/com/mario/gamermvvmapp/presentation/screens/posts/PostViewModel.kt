package com.mario.gamermvvmapp.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.domain.use_cases.posts.PostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(
    private val postsUseCase: PostsUseCase
) : ViewModel(){

    var postsResponse by mutableStateOf<Response<List<Post>>?>(null)


    init {
        getPost()
    }
    fun getPost() = viewModelScope.launch {

         postsResponse = Response.Loading

         postsUseCase.getPost().collect(){resp->
             postsResponse=resp
         }

    }


}
