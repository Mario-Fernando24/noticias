package com.mario.gamermvvmapp.presentation.screens.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.navigation.Graph
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPost(navController: NavController, viewModel: NewPostViewModel = hiltViewModel()){

    when(val postResponse=viewModel.savePostResponse){
        //si se encuentra en ese estado que muestre un progressBar que todavia se encuentra en proceso
        Response.Loading ->{
            ProgressBar()
        }

        is Response.Success->{
            Toast.makeText(LocalContext.current,"Publicación guardada correctamente", Toast.LENGTH_LONG).show()
            viewModel.clearForm()
            //efectos secundarios
         //   LaunchedEffect(Unit){

                //cuando la respuesta es exitosa nos envie a la siguiente pantalla
            //     navController.navigate(route = Graph.HOME){
                    //eliminamos el grafo anterior
            //      popUpTo(Graph.AUTHENTICATION ){  inclusive=true  }

            //  }
            //}

        }

        is Response.Failure->{
            Toast.makeText(LocalContext.current,"Hubo en error en la contraseña", Toast.LENGTH_LONG).show()
        }
    }
}