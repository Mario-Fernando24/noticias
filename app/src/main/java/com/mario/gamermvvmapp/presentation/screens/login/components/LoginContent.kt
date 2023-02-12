package com.mario.gamermvvmapp.presentation.screens.login.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.Darkgray700
import com.mario.gamermvvmapp.presentation.ui.theme.greyFondo

@Composable
fun LoginContent(viewModel: LoginViewModel= hiltViewModel()){

    Box(
        modifier = Modifier .fillMaxWidth(),
       //  horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .background(greyFondo)
        ){
            Column (
                modifier = Modifier.fillMaxWidth().padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier.height(100.dp).width(310.dp),
                    painter = painterResource(id= R.drawable.nttdata
                    ) ,
                    contentDescription = "Control de xbox 360")
                //ext(
                //    text = ""
                //)
            }

        }



     //FORMULARIO


        Card (
            modifier=Modifier.padding(start = 40.dp, end = 40.dp, top = 196.dp),
            backgroundColor = Darkgray700
        ){

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                Text(
                    modifier = Modifier.
                    padding(top = 40.dp, end = 0.dp, start = 0.dp, bottom = 0.dp),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.email.value,
                    onValueChange = {  viewModel.email.value = it},
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg =viewModel.EmailErrorMsg.value,
                    validateField = {
                        viewModel.validateEmail()
                    }

                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.PasswordErrorMsg.value,
                    validateField = {
                        viewModel.validatePassword()
                    }


                )

                DefaultButton(
                    text = "INICIAR SESIÓN",
                    onClick ={
                        Log.d("mario", "Email: ${viewModel.email.value}")
                        Log.d("mario", "password: ${viewModel.password.value}")

                    },
                    enable = viewModel.isEnableLoginButton
                )

            }
        }
    }
}

