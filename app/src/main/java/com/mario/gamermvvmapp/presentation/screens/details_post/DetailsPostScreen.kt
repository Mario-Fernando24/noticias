package com.mario.gamermvvmapp.presentation.screens.details_post

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.presentation.screens.details_post.components.DetailsPostContent
import com.mario.gamermvvmapp.presentation.screens.posts.PostViewModel
import com.mario.gamermvvmapp.presentation.screens.posts.components.GetPost

@Composable
fun DetailsPostScreen(navController:  NavHostController,post: String ,viewModel: PostViewModel = hiltViewModel() ){

    Scaffold(
        content = {
            DetailsPostContent()
        }
    )
}