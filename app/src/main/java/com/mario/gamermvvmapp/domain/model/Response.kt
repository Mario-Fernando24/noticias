package com.mario.gamermvvmapp.domain.model

//tener el control de lo que nos devuelve firebase
//PUEDE IR UN OBJETO GENERICO DE CUALQUIER TIPO
sealed class Response<out T>{

   //para saber si la respuesta actualmente se esta procesandose
   object Loading: Response<Nothing>()
   //para almacenar si el proceso del login se ejecuto exitosamente
   data class Success<out T>(val data: T): Response<T>()
    //si falla y recibimos una execcion
   data class Failure<out T>(val exception: Exception?): Response<T>()
}