package com.mario.gamermvvmapp.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.navigation.AppScreen

@Composable
fun ProfileScreen (navController: NavHostController, viewModel: ProfileViewModel= hiltViewModel()){

    Scaffold (

        topBar = {},
        content = {
            DefaultButton(
                text = "Cerrar session",
                onClick = {
                    viewModel.logout()
                    navController.navigate(route = AppScreen.Login.route)
                }
            )
        },


    )


}