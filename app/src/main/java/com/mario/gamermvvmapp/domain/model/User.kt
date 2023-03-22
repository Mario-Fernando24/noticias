package com.mario.gamermvvmapp.domain.model

import com.google.gson.Gson

//recibir los datos del usuario
data class User (
   var id:String = "",
    var username:String = "",
    var email:String = "",
    var password: String = "",
    var image: String = "",
    var city: String = ""
){
    fun toJson(): String = Gson().toJson(this)
    companion object{
        //recibe una data de tipo string y retorna un objeto de type users
        fun fromJson(data:String): User = Gson().fromJson(data, User::class.java)
    }

}


