package com.mario.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable

fun DialogDeletePostPinture(
    status:MutableState<Boolean>,
){

    if(status.value == true){
        AlertDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    onDismissRequest={ status.value = false },
                    backgroundColor = Color.White,
                    title = {
                        Text(
                            text = "Desea eliminar una publicación",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    },
                    buttons = {
                       Row(
                           modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
                           horizontalArrangement = Arrangement.SpaceAround
                       ) {
                           Button(
                               modifier = Modifier.width(130.dp),
                               onClick = {
                                   status.value = false
                                  /// pickImage()
                               }) {
                               Text(text = "No")
                           }
                           Button(
                               modifier = Modifier.width(130.dp),
                               onClick = {
                                   status.value = false
                                  // takePhoto()
                               }) {
                              Text(text = "Si")
                           }
                       }
                    }
            )
    }

}