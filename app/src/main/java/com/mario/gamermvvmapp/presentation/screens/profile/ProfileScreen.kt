package com.mario.gamermvvmapp.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.screens.profile.component.ProfileContent

@Composable
fun ProfileScreen (navController: NavHostController, viewModel: ProfileViewModel= hiltViewModel()){

    Scaffold (

        topBar = {},
        content = {
            ProfileContent(navController)
        },


    )


}