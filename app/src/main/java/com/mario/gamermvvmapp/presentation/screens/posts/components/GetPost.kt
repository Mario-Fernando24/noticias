package com.mario.gamermvvmapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.navigation.Graph
import com.mario.gamermvvmapp.presentation.screens.posts.PostViewModel

@Composable
fun GetPost( viewModel: PostViewModel = hiltViewModel()){

    when(val response=viewModel.postsResponse){
        Response.Loading ->{
            ProgressBar()
        }

        is Response.Success->{
            //si la respuesta es exito llamamos al postContent donde listara las publicaciones
            PostContents(posts = response.data)

        }

        is Response.Failure->{
            Toast.makeText(LocalContext.current,"Hubo un error interno, \n por favor intentar mas tarde", Toast.LENGTH_LONG).show()
        }
    }

}