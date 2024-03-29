package com.mario.gamermvvmapp.presentation.screens.register.component

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.navigation.AuthScreen
import com.mario.gamermvvmapp.presentation.navigation.Graph
import com.mario.gamermvvmapp.presentation.screens.register.RegisterViewModel


@Composable
fun Register(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()){

    when(val registerFlow=viewModel._registerFlow){
        //si se encuentra en ese estado que muestre un progressBar que todavia se encuentra en proceso
        Response.Loading ->{
            ProgressBar()
        }
        //cuando es una clase sellada utilizamos is
        is Response.Success->{

            //efectos secundarios
            LaunchedEffect(Unit){
                viewModel.createUser()
                navController.popBackStack(Graph.AUTHENTICATION, true)
                //cuando la respuesta es exitosa nos enviea la siguiente pantalla
                navController.navigate(route = Graph.HOME)
            }
        }

        is Response.Failure->{
            Log.d("mario","error al momento de loguearse")
            Toast.makeText(
                LocalContext.current, registerFlow.exception?.message ?: "Hubo en error en la contraseña",
                Toast.LENGTH_LONG).show()

        }
    }

}