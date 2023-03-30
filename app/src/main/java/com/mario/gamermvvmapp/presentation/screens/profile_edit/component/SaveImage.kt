package com.mario.gamermvvmapp.presentation.screens.profile_edit.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.ProgressBar
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel

@Composable
fun SaveImage(viewModel: ProfileEditViewModel= hiltViewModel()){

  when(val response = viewModel.saveImageResponse){
      Response.Loading->{
          ProgressBar()
      }
      is Response.Success -> {
          //una vez se almacene la imagen al storage lo agregamos al firestorage la url de la imagen
          viewModel.onUpdate(response.data)
      }
      is Response.Failure -> {

          Toast.makeText(LocalContext.current,"${response.exception?.message ?: "Error desconocido"}", Toast.LENGTH_LONG).show()
      }
  }

}