package com.mario.gamermvvmapp.presentation.screens.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.components.DefaultButton
import com.mario.gamermvvmapp.presentation.navigation.AppScreen
import com.mario.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.GamerMvvmAppTheme

@Composable
fun ProfileContent(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()){

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
       Box (){

           Image(
               modifier = Modifier.fillMaxWidth().height(200.dp),
               painter = painterResource(id = R.drawable.background),
               contentDescription =  "",
               contentScale = ContentScale.Crop,
               //opacar la imagen
               alpha = 0.6f
           )
           Column (
               modifier = Modifier.fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally
                   ){

               Spacer(modifier = Modifier.height(30.dp))
               Text("Bienvenido", fontSize = 30.sp, fontWeight = FontWeight.Bold)
               Spacer(modifier = Modifier.height(30.dp))
               Image(
                   modifier = Modifier.size(115.dp),
                   painter = painterResource(id = R.drawable.user),
                   contentDescription =  "")
           }
       }

        Spacer(modifier = Modifier.height(10.dp))
        Text("Nombre del usuario",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        Text("Email del usuario",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            icon = Icons.Default.Edit,
            text = "Editar datos",
            color = Color.White,
            onClick = {}

        )
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            icon = Icons.Default.Close,
            text = "Cerrar session",
            color = Color.Red,
            onClick = {
                viewModel.logout()
                 navController.navigate(route = AppScreen.Login.route){
                    popUpTo(AppScreen.Profile.route){ inclusive=true }
                }
            }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileContent() {
    GamerMvvmAppTheme (darkTheme = true){
        androidx.compose.material.Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ){
            ProfileContent (rememberNavController())
        }
    }
}