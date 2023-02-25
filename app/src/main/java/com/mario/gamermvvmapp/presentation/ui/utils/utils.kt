package com.mario.gamermvvmapp.presentation.ui.utils

import android.util.Log


fun isLettersOrDigits(chars: String): Boolean {

         return !chars.matches("^[a-zA-Z0-9]*$".toRegex())
    }
