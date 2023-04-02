package com.mario.gamermvvmapp.domain.model

data class Post(
    var name: String = "",
    var description: String = "",
    var privacy: String = "",
    var image: String = "",
    var idUser: String = "",
    //var user: User=User()
    var user: User?=null
)
