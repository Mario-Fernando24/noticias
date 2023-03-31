package com.mario.gamermvvmapp.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mario.gamermvvmapp.presentation.ui.theme.red700

@Composable

fun DefauldTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    errorMsg:String, //se dispara cada ves que el usuario empieza a teclear
    validateField: () -> Unit ={},

){

    Column (){

        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField()
                            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            label = {
                Text(text =label)
            },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.White
                )
            },
            visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None
        )

        Text(
            modifier = Modifier.padding(top = 5.dp, start = 20.dp),
            text = errorMsg,
            fontSize = 11.sp,
            color = Color.Red
        )



    }

}