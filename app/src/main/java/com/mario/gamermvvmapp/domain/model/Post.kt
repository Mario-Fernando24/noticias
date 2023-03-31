package com.mario.gamermvvmapp.domain.model

data class Post(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var privacy: String = "",
    var image: String = "",
    var idUser: String = ""

)
