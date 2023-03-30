package com.mario.gamermvvmapp.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostScreen
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navHostController: NavHostController ){

  navigation(
      route = Graph.DETAILS,
      startDestination = DetailsScreen.ProfileEdit.route
  ){

      //creando un nuevo post
      composable(route = DetailsScreen.NewPost.route){
          //la pantalla que abre esta ruta sera
          NewPostScreen(navController = navHostController)
      }

      composable(route= DetailsScreen.ProfileEdit.route,
          arguments = listOf(navArgument("user")
          {
              type = NavType.StringType
          })
      ){
          it.arguments?.getString("user")?.let { data->
             // ProfileEditScreen(navController, user = data)
              ProfileEditScreen(navController = navHostController, user =data)
          }

      }

  }

}


sealed class DetailsScreen(val route: String){

    //creando un nuevo post
    object NewPost: DetailsScreen("posts/new")

    object ProfileEdit: DetailsScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/$user"
    }

}