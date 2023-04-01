package com.mario.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mario.gamermvvmapp.domain.model.Post


//pasamos una lista de post (publicaciones)
@Composable
fun PostContents(posts: List<Post>){

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ){
        //cuales son los elementos que se van a mostrar
        items(items=posts){post->
            Text(text = post.name)

        }
    }
}