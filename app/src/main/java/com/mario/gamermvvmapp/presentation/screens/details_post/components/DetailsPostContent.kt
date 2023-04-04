package com.mario.gamermvvmapp.presentation.screens.details_post.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mario.gamermvvmapp.R
import com.mario.gamermvvmapp.presentation.screens.details_post.DetailsPostViewModel
import com.mario.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.mario.gamermvvmapp.presentation.ui.theme.red500

@Composable
fun DetailsPostContent(viewModel: DetailsPostViewModel = hiltViewModel()){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = viewModel.post.image,
            contentDescription = ""
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(vertical = 15.dp, horizontal = 15.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(10)
        ) {

            Row(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    model = viewModel.post.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(top = 3.dp, start = 20.dp)
                ) {
                    Text(
                        text = viewModel.post.user?.username ?: "",
                        fontSize = 13.sp
                    )

                    Text(
                        text = "Email",
                        fontSize = 11.sp
                    )

                }
            }

        }


        Text(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp),
            text = viewModel.post.name,
            fontSize = 20.sp,
            color = red500,
        )

        Divider(modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp), startIndent = 20.dp , thickness = 2.dp, color = Color.Gray)

        Card(
            modifier = Modifier.padding(start = 13.dp, bottom = 15.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(20.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 7.dp, horizontal = 20.dp)
            ) {

                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id= R.drawable.privacy) ,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(7.dp))

                Text(text = viewModel.post.privacy, fontWeight = FontWeight.Bold, fontSize = 17.sp)

            }


        }

        Divider(modifier = Modifier.padding(end = 20.dp, top = 10.dp, bottom = 10.dp), startIndent = 20.dp , thickness = 2.dp, color = Color.Gray)
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = "Descripcion", fontWeight = FontWeight.Bold, fontSize = 17.sp)
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = viewModel.post.description,
            fontSize = 13.sp)





    }

}