package com.mario.gamermvvmapp.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.components.DefaultTolbar
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.screens.profile_edit.component.ProfileEditContent
import com.mario.gamermvvmapp.presentation.screens.profile_edit.component.UpdateProfile

@Composable
fun ProfileEditScreen(navController: NavHostController, user:String){

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
     UpdateProfile(navController)

}