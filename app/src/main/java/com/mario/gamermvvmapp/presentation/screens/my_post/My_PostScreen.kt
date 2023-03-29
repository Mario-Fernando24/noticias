package com.mario.gamermvvmapp.presentation.screens.my_post

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MyPostScreen(navHostController: NavHostController){

    Scaffold(
        content = {
            Text(text = "My Post screen")
        }
    )
}