package com.mario.gamermvvmapp.presentation.screens.posts

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.screens.posts.components.GetPost
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun PostsScreen(navController: NavHostController, viewModel:PostViewModel = hiltViewModel() ){
    
    Scaffold(
        content = {
           GetPost(navController = navController)
        }
    )
}