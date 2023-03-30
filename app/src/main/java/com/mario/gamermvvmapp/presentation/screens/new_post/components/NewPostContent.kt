package com.mario.gamermvvmapp.presentation.screens.new_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefauldTextField
import com.mario.gamermvvmapp.presentation.ui.theme.GamerMvvmAppTheme
import com.mario.gamermvvmapp.presentation.ui.theme.greyFondo


data class PrivacyRadioButton(
    var nombre:String,
    var image: Int
)

@Composable
fun NewPostContent(){


     val radiOptions = listOf(
         PrivacyRadioButton("publico",R.drawable.public_relation),
         PrivacyRadioButton("solo amigos",R.drawable.friends),
         PrivacyRadioButton("privado",R.drawable.privacy)
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
                    .height(230.dp)
                    .background(greyFondo)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .height(100.dp)
                            .width(310.dp),
                        painter = painterResource(
                            id = R.drawable.add_image
                        ),
                        contentDescription = ""
                    )
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
                value = "",
                onValueChange = { },
                label = "Titulo de la publicación",
                icon = Icons.Default.Notifications,
                errorMsg = "",
                validateField = {

                }
            )

            DefauldTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                value = "",
                onValueChange = { },
                label = "Descripción",
                icon = Icons.Default.Notifications,
                errorMsg = "",
                validateField = {

                }
            )

            Text(
                 modifier = Modifier.padding(vertical = 15.dp),
                 text = "Privacidad",
                 fontSize = 17.sp,
                 fontWeight = FontWeight.Bold)

            radiOptions.forEach { option->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            //para saber cuando se selecciona alguna de las categoria
                            selected = false,
                            onClick = {}
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = false,
                        onClick = {  }
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

//visualizando los componentes
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GamerMvvmAppTheme (darkTheme = true){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewPostContent()
        }
    }
}