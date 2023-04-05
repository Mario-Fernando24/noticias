package com.mario.gamermvvmapp.presentation.screens.my_post.editarPost

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditarMyPostViewModel @Inject constructor(
        private val savedStateHandle: SavedStateHandle): ViewModel() {

    var state by mutableStateOf(EditState())

    // ARGUMENTS
    val data = savedStateHandle.get<String>("post")
    val post = data?.let { Post.fromJson(it) }

    init {
        state = state.copy(
            name = post?.name ?:"",
            description = post?.description ?:"",
            image = post?.image ?:"",
            privacy = post?.privacy ?:"",
        )


    }



}



