package com.mario.gamermvvmapp.presentation.screens.details_post

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mario.gamermvvmapp.domain.model.Post
import javax.inject.Inject

class DetailsPostViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle): ViewModel() {

    val dataa= savedStateHandle.get<String>("post")
    val post = Post.fromJson(dataa!!)






}