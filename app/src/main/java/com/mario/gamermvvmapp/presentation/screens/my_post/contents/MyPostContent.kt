package com.mario.gamermvvmapp.presentation.screens.my_post.contents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.presentation.screens.my_post.My_PostViewModel
import com.mario.gamermvvmapp.presentation.screens.posts.components.CardPost

@Composable
fun MyPostContents(
    navController: NavHostController
    ,posts: List<Post>, viewModel: My_PostViewModel) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 55.dp)
    ) {
        //cuales son los elementos que se van a mostrar
        items(items = posts) { post ->
            CardMyPost(navController,post = post, viewModel = viewModel)
        }
    }

}