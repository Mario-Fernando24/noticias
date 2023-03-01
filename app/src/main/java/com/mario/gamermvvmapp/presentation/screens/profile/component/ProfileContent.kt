package com.mario.gamermvvmapp.presentation.screens.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.ui.theme.GamerMvvmAppTheme

@Composable
//fun ProfileContent(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()){
fun ProfileContent(){

    Column (
        modifier = Modifier.fillMaxSize()){
       Box (){

           Image(
               modifier = Modifier.fillMaxWidth(),
               painter = painterResource(id = R.drawable.background),
               contentDescription =  "",
               contentScale = ContentScale.Crop
           )
       }

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
            ProfileContent ()
        }
    }
}