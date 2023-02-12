package com.mario.gamermvvmapp.presentation.components

import android.icu.text.CaseMap
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.ui.theme.red500

@Composable
fun DefaultTolbar(title: String,upAvailable: Boolean= false, navController: NavHostController ? = null) {

    TopAppBar(
        backgroundColor = red500,
        title = {
            Text(text = title,
            fontSize = 19.sp
            )
        },
        navigationIcon = {
            //si el usuario quiere habilitar el boton atras
            if(upAvailable){
                IconButton(onClick = { navController?.popBackStack()  }){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    )
}