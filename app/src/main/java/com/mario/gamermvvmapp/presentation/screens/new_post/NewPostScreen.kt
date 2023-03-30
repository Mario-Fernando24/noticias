package com.mario.gamermvvmapp.presentation.screens.new_post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.components.DefaultTolbar
import com.mario.gamermvvmapp.presentation.screens.new_post.components.NewPostContent

@Composable
fun NewPostScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            DefaultTolbar(title = "Nuevo Post",
                upAvailable = true,
                navController=null)
        },
        content = {
            NewPostContent()
        },

        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, bottom = 20.dp),
                text = "Publicar",
                onClick = { /*TODO*/ })
        }
    )



}