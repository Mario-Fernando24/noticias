package com.mario.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mario.gamermvvmapp.presentation.screens.home.HomeScreen
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen

@Composable
fun RootNavGraph(navController:  NavHostController) {

   NavHost(
       navController = navController,
       route=Graph.ROOT,
       //esta es la primera pantalla que se mostrara al correr la app por primera vez
       startDestination = Graph.AUTHENTICATION
   ){
       authNavGraph(navController = navController)

       composable(route = Graph.HOME){
           HomeScreen()
       }

    // composable(route= AuthScreen.Profile.route){
      //     ProfileScreen(navController)
      // }
       composable(route= AuthScreen.ProfileEdit.route,
           arguments = listOf(navArgument("user")
          {
            type = NavType.StringType
          })
          ){
           it.arguments?.getString("user")?.let { data->
               ProfileEditScreen(navController, user = data)
           }

       }
   }

}

sealed class RootScreen(val route: String) {
    object Home : RootScreen("home")
}
