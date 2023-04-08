package com.mario.gamermvvmapp.presentation.screens.my_post.editarPost.contents

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.components.DialogCapturePinture
import com.mario.gamermvvmapp.presentation.screens.my_post.editarPost.EditarMyPostViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.greyFondo

@Composable
fun EditMyPostContents(viewModel: EditarMyPostViewModel = hiltViewModel()) {


    val state = viewModel.state

    viewModel.resultingActivityHandler.handle()

    var dialogState = remember {mutableStateOf(false)}

    DialogCapturePinture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = { viewModel.pickImage() }
    )

    Box(
        modifier = Modifier .fillMaxWidth(),
        //  horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(greyFondo)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(viewModel.userImageShow!=""){ //Si se selecciono una imagen
                        AsyncImage(
                            modifier = Modifier
                                .width(1600.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            model = viewModel.userImageShow,
                            contentDescription = "Selected image",
                            // contentScale = ContentScale.Crop
                        )

                    }else{

                        Image(
                            modifier = Modifier
                                .height(140.dp)
                                .clickable {
                                    dialogState.value = true
                                }
                                .width(310.dp),
                            painter = painterResource(id= R.drawable.add_image
                            ) ,
                            contentDescription = "Control de xbox 360")

                    }
                    //https://www.flaticon.es/
                    Text(text = "SELECCIONA UNA IMAGEN",
                        color = Color.Black,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            DefauldTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp),
                value = state.name,
                onValueChange = { viewModel.onNameInput(it) },
                label = "Titulo de la publicación",
                icon = Icons.Default.Notifications,
                errorMsg ="",
                validateField = {
                //    viewModel.validateName()
                }
            )

            DefauldTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                value = state.description,
                onValueChange = { viewModel.onDescriptionInput(it) },
                label = "Descripción",
                icon = Icons.Default.Notifications,
                 errorMsg = "",
                validateField = {
                    //    viewModel.validateDescription()
                }
            )



            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = "Privacidad",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold)

            viewModel.radiOptions.forEach { option->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            //para saber cuando se selecciona alguna de las categoria
                            selected = (option.nombre == state.privacy),
                            onClick = {
                               viewModel.onPrivacyInput(option.nombre)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = (option.nombre == state.privacy),
                        onClick = {
                            viewModel.onPrivacyInput(option.nombre)
                        }
                    )
                    Row() {

                        Text(
                            modifier= Modifier.width(100.dp),
                            text = option.nombre
                        )

                        Image(
                            modifier= Modifier
                                .height(40.dp)
                                .padding(bottom = 20.dp),
                            painter = painterResource(id = option.image),
                            contentDescription = "")

                    }

                }
            }

        }
     }

    }
