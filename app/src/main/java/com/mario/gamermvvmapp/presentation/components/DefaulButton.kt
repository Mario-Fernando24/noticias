package com.mario.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mario.gamermvvmapp.presentation.ui.theme.red500
import com.mario.gamermvvmapp.presentation.ui.theme.red700

@Composable
fun DefaultButton(
    text: String,
    //fyunction landa
    onClick: () -> Unit,
    color: Color = red500,
    icon: ImageVector = Icons.Default.ArrowForward,
    enable:Boolean = true,
    modifier : Modifier =Modifier.fillMaxWidth().padding(vertical = 10.dp),
   // errorMsg: String
){

    Column() {

        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            enabled = enable

        ){
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }



    }


}