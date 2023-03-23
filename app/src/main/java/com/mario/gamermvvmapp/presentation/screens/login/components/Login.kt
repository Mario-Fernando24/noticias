package com.mario.gamermvvmapp.presentation.screens.login.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.navigation.AppScreen
import com.mario.gamermvvmapp.presentation.screens.login.LoginViewModel


@Composable
fun Login(navController: NavController,viewModel: LoginViewModel= hiltViewModel()){

    when(val loginResponse=viewModel.loginResponse){
        //si se encuentra en ese estado que muestre un progressBar que todavia se encuentra en proceso
        Response.Loading ->{
            ProgressBar()
        }

        is Response.Success->{
            //efectos secundarios
            LaunchedEffect(Unit){
                //cuando la respuesta es exitosa nos envie a la siguiente pantalla
                navController.navigate(route = AppScreen.Profile.route){
                    popUpTo(AppScreen.Login.route){  inclusive=true  }
                }
            }
        }

        is Response.Failure->{
            Toast.makeText(LocalContext.current,"Hubo en error en la contraseña", Toast.LENGTH_LONG).show()
        }
    }
}