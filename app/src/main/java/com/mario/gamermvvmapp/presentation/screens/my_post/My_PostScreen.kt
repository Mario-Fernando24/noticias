package com.mario.gamermvvmapp.presentation.screens.my_post

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.presentation.navigation.DetailsScreen
import com.mario.gamermvvmapp.presentation.screens.my_post.contents.GetMyPost
import com.mario.gamermvvmapp.presentation.screens.my_post.editarPost.EditarMyPostViewModel

@Composable
fun MyPostScreen(navController: NavHostController, viewModel: EditarMyPostViewModel = hiltViewModel()){

    Scaffold(

        content = {
            GetMyPost(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 50.dp),
                onClick = {
                navController.navigate(DetailsScreen.NewPost.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "")
            }
        }
    )
}