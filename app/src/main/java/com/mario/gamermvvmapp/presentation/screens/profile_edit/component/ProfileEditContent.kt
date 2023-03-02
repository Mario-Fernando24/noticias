package com.mario.gamermvvmapp.presentation.screens.profile_edit.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.mario.gamermvvmapp.presentation.screens.register.RegisterViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.Darkgray700
import com.mario.gamermvvmapp.presentation.ui.theme.red500

@Composable
fun ProfileEditContent (navController: NavController, viewModel: ProfileEditViewModel = hiltViewModel()){

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
            modifier=Modifier.padding(start = 40.dp, end = 40.dp, top = 180.dp),
            backgroundColor = Darkgray700
        ){

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()).padding(horizontal = 20.dp)

            ) {

                Text(
                    modifier = Modifier.
                    padding(top = 10.dp, end = 0.dp, start = 0.dp, bottom = 0.dp),
                    text = "Editar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Dios Te ama",
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


                DefaultButton(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 40.dp ),
                    icon = Icons.Default.Edit,
                    text = "ACTUALIZAR DATOS",
                    onClick ={
                       // viewModel.onRegister()
                    },
                 //   enable =  viewModel.isEnableRegisterButton
                )

            }
        }
    }

}





