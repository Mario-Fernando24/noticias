package com.mario.gamermvvmapp.presentation.navigation
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mario.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mario.gamermvvmapp.presentation.screens.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable( route =  AuthScreen.Login.route){
            LoginScreen(navController)
        }
        composable(route= AuthScreen.Register.route){
            RegisterScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String){

    //clases selladas  donde definimos las rutas
    object Login: AuthScreen("login")
    object Register: AuthScreen("register")




}