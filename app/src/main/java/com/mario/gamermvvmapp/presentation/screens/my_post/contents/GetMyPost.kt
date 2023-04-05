package com.mario.gamermvvmapp.presentation.screens.my_post.contents

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.screens.my_post.My_PostViewModel
import com.mario.gamermvvmapp.presentation.screens.posts.PostViewModel

@Composable
fun GetMyPost(navController: NavHostController ,viewModel: My_PostViewModel = hiltViewModel()){

    when(val response=viewModel.mypostsResponse){
        Response.Loading ->{
            ProgressBar()
        }

        is Response.Success->{
            //si la respuesta es exito llamamos al postContent donde listara las publicaciones
            MyPostContents(navController = navController,posts = response.data, viewModel)

        }

        is Response.Failure->{
            Toast.makeText(LocalContext.current,"Hubo un error interno, \n por favor intentar mas tarde", Toast.LENGTH_LONG).show()
        }
    }

}