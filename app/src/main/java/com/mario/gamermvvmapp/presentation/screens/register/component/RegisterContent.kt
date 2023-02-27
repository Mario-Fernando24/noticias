package com.mario.gamermvvmapp.presentation.screens.register.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.domain.model.Response
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.navigation.AppScreen
import com.mario.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.mario.gamermvvmapp.presentation.screens.register.RegisterViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.Darkgray700
import com.mario.gamermvvmapp.presentation.ui.theme.greyFondo
import com.mario.gamermvvmapp.presentation.ui.theme.red500

@Composable
fun RegisterContent (navController: NavController, viewModel: RegisterViewModel = hiltViewModel()){

    //para traernos el estado en el cual se encuentra nuestra petición
    val registerFlow = viewModel.registerFlow.collectAsState()

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
                    modifier = Modifier.height(140.dp).width(310.dp),
                    painter = painterResource(id= R.drawable.user
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
                    value = viewModel.userName.value,
                    onValueChange = {  viewModel.userName.value = it},
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Text,
                    errorMsg =viewModel.userNameErrorMsg.value,
                    validateField = {
                        viewModel.validateUserName()
                    }
                )

                DefauldTextField(
                    modifier = Modifier.padding(top = 5.dp),
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

                DefauldTextField(
                    modifier = Modifier.padding(top = 0.dp),
                    value = viewModel.confirPassword.value,
                    onValueChange = { viewModel.confirPassword.value = it },
                    label = "Confirmar contraseña",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.confirPasswordErrorMsg.value,
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

    registerFlow.value.let {
        when(it){
            //si se encuentra en ese estado que muestre un progressBar que todavia se encuentra en proceso
            Response.Loading ->{
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator()
                }
            }
           //cuando es una clase sellada utilizamos is
            is Response.Success->{
                //efectos secundarios
                LaunchedEffect(Unit){
                    navController.popBackStack(AppScreen.Login.route, true)
                    //cuando la respuesta es exitosa nos envie a la siguiente pantalla
                    navController.navigate(route = AppScreen.Profile.route)
                }
            }

            is Response.Failure->{
                Log.d("mario","error al momento de loguearse")
                Toast.makeText(
                    LocalContext.current, it.exception?.message ?: "Hubo en error en la contraseña",
                    Toast.LENGTH_LONG).show()

            }
        }
    }
}








@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    //RegisterContent()
}