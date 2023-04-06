package com.mario.gamermvvmapp.presentation.screens.my_post.contents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.domain.model.Post
import com.mario.gamermvvmapp.domain.model.User
import com.mario.gamermvvmapp.presentation.navigation.DetailsScreen
import com.mario.gamermvvmapp.presentation.screens.my_post.My_PostViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.red500
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun CardMyPost(navController: NavHostController,post: Post, viewModel: My_PostViewModel){

    Card(
        elevation = 4.dp,
        //bordes redondeados
        shape = RoundedCornerShape(20.dp),
        contentColor = Color.White,
        modifier = Modifier
            .padding(top = 15.dp)
           // .clickable {
           //     navController.navigate(route = DetailsScreen.DetailsPost.passPost(post.toJson()))
           // }
    ) {

        Column() {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )



            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp),
                text = post.name,
                fontSize = 12.sp,
            )

            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 4.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

            Row(
                Modifier
                    .fillMaxWidth().padding(start = 250.dp,bottom = 15.dp )
                    .height(30.dp)
            ) {

                IconButton(
                    onClick = {
                        viewModel.delete(post.id)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Color.White
                    )
                }


                IconButton(

                        onClick = {
                          //  post.image= URLEncoder.encode(post.image,
                            //   StandardCharsets.UTF_8.toString())
                            navController.navigate(route = DetailsScreen.EditMyPost.passPostEdit(post.toJson()))
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = red500
                    )
                }

            }

        }

    }
}
