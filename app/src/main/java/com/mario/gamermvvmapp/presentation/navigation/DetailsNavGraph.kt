package com.mario.gamermvvmapp.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.presentation.screens.details_post.DetailsPostScreen
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostScreen
import com.mario.gamermvvmapp.presentation.screens.new_post.NewPostViewModel
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController){

  navigation(
      route = Graph.DETAILS,
      startDestination = DetailsScreen.ProfileEdit.route
  ){

      //creando un nuevo post
      composable(route = DetailsScreen.NewPost.route){
          //la pantalla que abre esta ruta sera
         // NewPostScreen(navController = navHostController)
          NewPostScreen(navController = navController)
      }

      composable(route= DetailsScreen.ProfileEdit.route,
          arguments = listOf(navArgument("user")
          {
              type = NavType.StringType
          })
      ){
          it.arguments?.getString("user")?.let { data->
             // ProfileEditScreen(navController, user = data)
              ProfileEditScreen(navController = navController, user =data)
          }

      }


      //Detalle de la publicacion

      composable(route= DetailsScreen.DetailsPost.route,
          arguments = listOf(navArgument("post")
          {
              type = NavType.StringType
          })
      ){
          it.arguments?.getString("post")?.let { data->
              // abrimos la pantalla
              DetailsPostScreen(navController = navController, post = data)
          }

      }

  }

}


sealed class DetailsScreen(val route: String){

    //creando un nuevo post
    object NewPost: DetailsScreen("posts/new")

    //detalle de las publicaciones
    object ProfileEdit: DetailsScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/$user"
    }

    object DetailsPost: DetailsScreen("post/details/{post}"){
        fun passPost(post: String) = "post/details/$post"
    }

}