package com.mario.gamermvvmapp.presentation.screens.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.presentation.components.DefaultTolbar
import com.mario.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mario.gamermvvmapp.presentation.screens.login.components.LoginBottonBar
import com.mario.gamermvvmapp.presentation.screens.login.components.LoginContent
import com.mario.gamermvvmapp.presentation.screens.register.component.Register
import com.mario.gamermvvmapp.presentation.screens.register.component.RegisterContent
import com.mario.gamermvvmapp.presentation.ui.theme.GamerMvvmAppTheme

@Composable
fun RegisterScreen(navController: NavHostController) {

    Scaffold (
        topBar = {
            DefaultTolbar(
                title = "Nuevo usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            RegisterContent(navController)
        },
        bottomBar = {

        }
    )
    Register(navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    RegisterScreen(rememberNavController())
}