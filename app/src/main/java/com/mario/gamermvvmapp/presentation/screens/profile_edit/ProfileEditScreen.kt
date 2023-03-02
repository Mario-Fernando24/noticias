package com.mario.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.components.DefaultTolbar
import com.mario.gamermvvmapp.presentation.screens.profile_edit.component.ProfileEditContent

@Composable
fun ProfileEditScreen(navController: NavHostController){

    Scaffold (
        topBar = {
            DefaultTolbar(
                title = "Editar usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {

        }
    )
}