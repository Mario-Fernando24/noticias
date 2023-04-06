package com.mario.gamermvvmapp.presentation.screens.my_post.editarPost.contents

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.screens.my_post.editarPost.EditarMyPostViewModel


@Composable
fun UpdateMyPost(navController: NavController, viewModel: EditarMyPostViewModel = hiltViewModel()){

    when(val updateFlow=viewModel._updateFlow){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success->{
            Toast.makeText(
                LocalContext.current,  "Publicación actualizado correctamente",
                Toast.LENGTH_LONG).show()
        }

        is Response.Failure->{
            Toast.makeText(
                LocalContext.current, updateFlow.exception?.message ?: "Hubo en error al actualizar la publicación",
                Toast.LENGTH_LONG).show()

        }
    }

}