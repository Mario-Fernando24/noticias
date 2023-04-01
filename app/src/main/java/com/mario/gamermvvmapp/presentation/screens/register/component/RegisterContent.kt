package com.mario.gamermvvmapp.presentation.screens.register.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.screens.register.RegisterViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.Darkgray700
import com.mario.gamermvvmapp.presentation.ui.theme.red500

@Composable
fun RegisterContent (navController: NavController, viewModel: RegisterViewModel = hiltViewModel()){

    Box(
        modifier = Modifier .fillMaxWidth(),
        //  horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(red500)
        ){
            Column (
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier.height(120.dp).width(330.dp),
                    painter = painterResource(id= R.drawable.travel
                    ) ,
                    contentDescription = "Control de xbox 360")
                //ext(
                //    text = ""
                //)
            }

        }

        //FORMULARIO

        Card (
            modifier=Modifier.padding(start = 40.dp, end = 40.dp, top = 170.dp),
            backgroundColor = Darkgray700
        ){

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 20.dp)

            ) {

                Text(
                    modifier = Modifier.
                    padding(top = 10.dp, end = 0.dp, start = 0.dp, bottom = 0.dp),
                    text = "Registrate",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Por favor ingrese estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.userName,
                    onValueChange = {  viewModel.userName = it},
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Text,
                    errorMsg =viewModel.userNameErrorMsg,
                    validateField = {
                        viewModel.validateUserName()
                    }
                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.email,
                    onValueChange = {  viewModel.email = it},
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg =viewModel.EmailErrorMsg,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.PasswordErrorMsg,
                    validateField = {
                        viewModel.validatePassword()
                    }

                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = viewModel.confirPassword,
                    onValueChange = { viewModel.confirPassword = it },
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.confirPasswordErrorMsg,
                    validateField = {
                        viewModel.validateconfirmPassword()
                    }

                )

                DefaultButton(

                    text = "REGISTRATE",
                    onClick ={
                       viewModel.onRegister()
                    },
                    enable =  viewModel.isEnableRegisterButton
                )

            }
        }
    }

}








@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    //RegisterContent()
}