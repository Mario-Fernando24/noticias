package com.mario.gamermvvmapp.domain.use_cases.auth


//todos los casos de uso relacionados con la autentificacion register, login y cierre de session
data class AuthUseCases (

   val getCurrentUserg: GetCurrentUser,
   val login: Login,
   val logout: Logout


)