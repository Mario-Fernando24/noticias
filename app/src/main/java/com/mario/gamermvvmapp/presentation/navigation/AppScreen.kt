package com.mario.gamermvvmapp.presentation.navigation

import com.mario.gamermvvmapp.domain.model.User


sealed class AppScreen(val route: String){
//clases selladas  donde definimos las rutas

    object Login: AppScreen("login")
    object Register: AppScreen("register")
    object Profile: AppScreen("profile")
    object ProfileEdit: AppScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/$user"
    }



}
