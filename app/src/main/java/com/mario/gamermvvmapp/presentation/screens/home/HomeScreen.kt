package com.mario.gamermvvmapp.presentation.screens.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.presentation.navigation.HomeButtonBarGraph
import com.mario.gamermvvmapp.presentation.navigation.HomeButtonBarScreen

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {

    //recibe nuestro archivo de navegación
    Scaffold(
        bottomBar = {
            BottonBar(navController = navController)
        }
    ){
         HomeButtonBarGraph(navController = navController)
    }

}


@Composable
fun BottonBar(navController: NavHostController){

    val screen = listOf(
        HomeButtonBarScreen.Posts,
        HomeButtonBarScreen.MyPosts,
        HomeButtonBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currenDestination = navBackStackEntry?.destination
    val bottomBarDestination  = screen.any { it.route==currenDestination?.route }

    if(bottomBarDestination==true){
        BottomNavigation() {
            //recorremos cada uno
            screen.forEach { screen->
                AddItem(
                    screen = screen,
                    currenDestination = currenDestination,
                    navController = navController)
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen:HomeButtonBarScreen,
    currenDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon={
          Icon(imageVector = screen.icon, contentDescription ="Navigation Icon" )
        },
        //para saber que ruta esta seleccionada actualmente
        selected = currenDestination?.hierarchy?.any {
               it.route == screen.route
        }==true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
