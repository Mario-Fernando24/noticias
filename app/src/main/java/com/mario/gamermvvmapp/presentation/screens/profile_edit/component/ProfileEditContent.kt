package com.mario.gamermvvmapp.presentation.screens.profile_edit.component

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.mario.gamermvvmapp.presentation.screens.register.RegisterViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.Darkgray700
import com.mario.gamermvvmapp.presentation.ui.theme.red500
import com.mario.gamermvvmapp.presentation.utils.ComposeFileProvider

@Composable
fun ProfileEditContent (navController: NavController, viewModel: ProfileEditViewModel = hiltViewModel()){

    //agregar imagen de la galeria del dispositivo
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        // nos devuelve una url
        onResult = {url->
            if(url!=null){
                viewModel.onGalleryResult(url)
            }
        })


    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        //nos devuelve un bool
        onResult = {url->
            if(url!=null){
                viewModel.onCameraResult(url)
            }
        })

    val context = LocalContext.current

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
             if(viewModel.hasImage && viewModel.imageUrl!=null){
                 //Si se selecciono una imagen 
                 AsyncImage(
                     modifier = Modifier.height(140.dp).clip(CircleShape),
                     model = viewModel.imageUrl,
                     contentDescription = "Selected image")
             }else{

                 Image(
                     modifier = Modifier
                         .height(140.dp)
                         .clickable {
                          //   imagePicker.launch("image/*")
                             val uri = ComposeFileProvider.getImageUrl(context)
                             viewModel.imageUrl = uri
                             cameraLauncher.launch(uri)
                         }
                         .width(310.dp),
                     painter = painterResource(id= R.drawable.user
                     ) ,
                     contentDescription = "Control de xbox 360")

             }
            }

        }

        //FORMULARIO

        Card (
            modifier=Modifier.padding(start = 40.dp, end = 40.dp, top = 180.dp),
            backgroundColor = Darkgray700
        ){

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)

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

                DefauldTextField(
                    modifier = Modifier.padding(top=0.dp),
                    value = viewModel.userCity,
                    onValueChange = { viewModel.userCity = it},
                    label = "Ciudad",
                    icon = Icons.Default.Done,
                    errorMsg =  viewModel.userNameErrorMsg,
                    validateField = {
                        viewModel.validateUserCity()
                    }

                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    icon = Icons.Default.Edit,
                    text = "ACTUALIZAR DATOS",
                    onClick ={
                        viewModel.onUpdate()
                    },
                 //   enable =  viewModel.isEnableRegisterButton
                )

            }
        }
    }

}





