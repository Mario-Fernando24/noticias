package com.mario.gamermvvmapp.presentation.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.presentation.screens.login.components.LoginBottonBar
import com.mario.gamermvvmapp.presentation.screens.login.components.LoginContent
import com.mario.gamermvvmapp.presentation.ui.theme.GamerMvvmAppTheme

//creo un componente
@Composable
fun LoginScreen (navController: NavHostController){

    Scaffold (
        topBar = {},
        content = {
            LoginContent()
            },
        bottomBar = {
            LoginBottonBar(navController)
        }
    )
}


//visualizando los componentes
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GamerMvvmAppTheme (darkTheme = true){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen (rememberNavController())
        }
    }
}