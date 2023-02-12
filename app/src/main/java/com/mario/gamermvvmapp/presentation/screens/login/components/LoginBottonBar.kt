package com.mario.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mario.gamermvvmapp.presentation.navigation.AppScreen
import com.mario.gamermvvmapp.presentation.ui.theme.red500

@Composable
fun LoginBottonBar (navController: NavHostController){
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 0.dp, end = 0.dp, start = 0.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No tienes cuenta ?",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AppScreen.Register.route)
            },
            text = "REGISTRATE AQUI",
            color = red500,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginBottonBar() {
    LoginBottonBar (rememberNavController())
}
