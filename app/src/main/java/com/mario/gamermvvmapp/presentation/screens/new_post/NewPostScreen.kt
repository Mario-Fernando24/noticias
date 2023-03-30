package com.mario.gamermvvmapp.presentation.screens.new_post

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.screens.new_post.components.NewPostContent

@Composable
fun NewPostScreen(navController: NavHostController){

    Scaffold(
        content = {
            NewPostContent()
        }
    )



}