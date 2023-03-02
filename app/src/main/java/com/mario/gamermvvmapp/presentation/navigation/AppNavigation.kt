package com.mario.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mario.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mario.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.mario.gamermvvmapp.presentation.screens.register.RegisterScreen

@Composable
fun AppNavigation(navController:  NavHostController) {

   NavHost(
       navController = navController,
       //esta es la primera pantalla que se mostrara al correr la app por primera vez
       startDestination = AppScreen.Login.route
   ){
       composable( route =  AppScreen.Login.route){
                     LoginScreen(navController)
       }
       composable(route= AppScreen.Register.route){
               RegisterScreen(navController)
       }
       composable(route= AppScreen.Profile.route){
           ProfileScreen(navController)
       }
       composable(route= AppScreen.ProfileEdit.route){
           ProfileEditScreen(navController)
       }
   }

}