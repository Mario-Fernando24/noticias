package com.mario.gamermvvmapp.presentation.screens.my_post.editarPost

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.components.DefaultTolbar
import com.mario.gamermvvmapp.presentation.screens.my_post.My_PostViewModel
import com.mario.gamermvvmapp.presentation.screens.my_post.editarPost.contents.EditMyPostContents
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostViewModel
import com.mario.gamermvvmapp.presentation.screens.new_post.components.NewPostContent

@Composable
fun EditarMyPostScreen(navController:  NavHostController,post: String, viewModel: EditarMyPostViewModel = hiltViewModel()){

    Scaffold(
        topBar = {
            DefaultTolbar(title = "Editar "+viewModel.state.name,
                upAvailable = true,
                navController=null)
        },
        content = {
            EditMyPostContents()
        },

        bottomBar = {
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 20.dp),
                text = "Editar",
                onClick = {
                    viewModel.onUpdatePost()
                })
        }
    )
}