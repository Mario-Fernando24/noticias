package com.mario.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mario.gamermvvmapp.domain.model.Post


//pasamos una lista de post (publicaciones)
@Composable
fun PostContents(posts: List<Post>){

    LazyColumn(
        modifier = Modifier.fillMaxWidth().
                padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 55.dp)
    ){
        //cuales son los elementos que se van a mostrar
        items(items=posts){post->
            CardPost(post = post)
        }
    }
}