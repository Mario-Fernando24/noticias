package com.mario.gamermvvmapp.domain.model

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class Post(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var privacy: String = "",
    var image: String = "",
    var idUser: String = "",
    //var user: User=User()
    var user: User?=null,
    //array vacio
    var likes: ArrayList<String> = ArrayList()

){
    fun toJson(): String = Gson().toJson(Post(
        id,
        name,
        description,
        privacy,
        if (image != "") URLEncoder.encode(image, StandardCharsets.UTF_8.toString()) else "",
        idUser,
        User(
            id = user?.id ?: "",
            username = user?.username ?: "",
            email = user?.email ?: "",
            image =
            if (!user?.image.isNullOrBlank())
                URLEncoder.encode(user?.image, StandardCharsets.UTF_8.toString())
            else "",
        ),
        likes
    ))

    companion object {
        fun fromJson(data: String): Post = Gson().fromJson(data, Post::class.java)

    }
}
