package com.mario.gamermvvmapp.presentation.screens.profile_edit.component

import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.navigation.AppScreen
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.mario.gamermvvmapp.presentation.screens.register.RegisterViewModel


@Composable
fun UpdateProfile(navController: NavController, viewModel: ProfileEditViewModel = hiltViewModel()){

    when(val updateFlow=viewModel._updateFlow){
        Response.Loading ->{
            ProgressBar()
        }
        is Response.Success->{
            Toast.makeText(
                LocalContext.current,  "Usuario actualizado correctamente",
                Toast.LENGTH_LONG).show()
        }

        is Response.Failure->{
            Toast.makeText(
                LocalContext.current, updateFlow.exception?.message ?: "Hubo en error al actualizar el usuario",
                Toast.LENGTH_LONG).show()

        }
    }

}