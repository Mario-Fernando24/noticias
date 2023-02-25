package com.mario.gamermvvmapp.presentation.navigation

sealed class AppScreen(val route: String){
//clases selladas  donde definimos las rutas

    object Login: AppScreen("login")
    object Register: AppScreen("register")
    object Profile: AppScreen("profile")


}
