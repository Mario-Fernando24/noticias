package com.mario.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.domain.model.Post

@Composable
fun CardPost(post: Post){

    Card(elevation = 4.dp,
        //bordes redondeados
        shape = RoundedCornerShape(20.dp),
        contentColor = Color.White,
        modifier = Modifier.padding(top = 15.dp)
    ) {

        Column() {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop)



            Row(
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, top = 10.dp)
            ) {

                if( post.user.image!=""){ //Si se selecciono una imagen
                    AsyncImage(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .clip(CircleShape),
                        model = post.user.image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        painter = painterResource(id= R.drawable.user
                        ) ,
                        contentDescription = "",
                        contentScale = ContentScale.Crop)
                }

                Text(
                    modifier=Modifier.padding(top = 10.dp, start = 10.dp),
                    text = post.user.username,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }



            Text(
                modifier=Modifier.padding(horizontal = 15.dp, vertical = 2.dp),
                text = post.name,
                fontSize = 12.sp,
            )

            Text(
                modifier=Modifier.padding(horizontal = 15.dp, vertical = 4.dp),
                text = post.description,
                 fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )


            
        }

    }

}