package com.mario.gamermvvmapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.presentation.navigation.AppNavigation
import com.mario.gamermvvmapp.presentation.ui.theme.GamerMvvmAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    //private lateinit var navController: NavController
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //relaciona con
            GamerMvvmAppTheme (darkTheme = true){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //pantalla principal
                    navController = rememberNavController()
                    AppNavigation(navController= navController)
                  //  LoginScreen()
                }
            }
        }
    }
}

