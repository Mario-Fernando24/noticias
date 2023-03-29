package com.mario.gamermvvmapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.presentation.navigation.HomeButtonBarGraph

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    //recibe nuestro archivo de navegación
    Scaffold(
        topBar = { Text(text = "Home")}
    ){
         HomeButtonBarGraph(navController = navController)
    }

}

