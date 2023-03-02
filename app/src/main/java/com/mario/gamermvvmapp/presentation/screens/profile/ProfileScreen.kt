package com.mario.gamermvvmapp.presentation.screens.profile

import android.provider.ContactsContract
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.navigation.AppScreen
import com.mario.gamermvvmapp.presentation.screens.profile.component.ProfileContent
import com.mario.gamermvvmapp.presentation.screens.profile_edit.component.ProfileEditContent

@Composable
fun ProfileScreen (navController: NavHostController, viewModel: ProfileViewModel= hiltViewModel()){

    Scaffold (

        topBar = {},
        content = {
            ProfileContent(navController)
        },


    )


}