package com.mario.gamermvvmapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mario.gamermvvmapp.presentation.screens.my_post.MyPostScreen
import com.mario.gamermvvmapp.presentation.screens.posts.PostsScreen
import com.mario.gamermvvmapp.presentation.screens.profile.ProfileScreen

@Composable
fun HomeButtonBarGraph(navController: NavHostController){
    
   NavHost(
    navController = navController,
       route = Graph.HOME,
       startDestination = HomeButtonBarScreen.Posts.route
   ){

       composable(route= HomeButtonBarScreen.Posts.route){
           PostsScreen(navController)
       }


      composable(route= HomeButtonBarScreen.MyPosts.route){
           MyPostScreen(navController)
       }

       composable(route= HomeButtonBarScreen.Profile.route) {
           ProfileScreen(navController)
       }

       detailsNavGraph(navController)
   }
}



sealed class HomeButtonBarScreen(

    val route: String,
    var title:String,
    val icon: ImageVector
){

    //primera pestaña
    object Posts: HomeButtonBarScreen(
        route = "posts",
        title = "Posts",
        icon = Icons.Default.List
    )
    object MyPosts: HomeButtonBarScreen(
        route = "my_post",
        title = "Mis post",
        icon = Icons.Outlined.List
    )

    object Profile: HomeButtonBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}