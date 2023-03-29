package com.mario.gamermvvmapp.presentation.screens.posts

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun PostsScreen(navHostController: NavHostController){
    
    Scaffold(
        content = {
            Text(text = "PostScreen")
        }
    )
}