package com.mario.gamermvvmapp.presentation.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.mario.gamermvvmapp.R
import java.io.File

//hereda de FileProvider para las imagenes que estan en la carpeta R.xml.
class ComposeFileProvider: FileProvider(R.xml.file_paths){

     companion object{
         //la funcion nos devuelve una url
         fun getImageUrl(context: Context): Uri{
             //creamos un directorio
             val directory = File(context.cacheDir,"images")
             //accedemos al directorio
             directory.mkdirs()
             val file = File.createTempFile(
                 "selected_image_",
                 ".jpg",
                 directory
             )
             val authority = context.packageName + ".fileprovider"

             return getUriForFile(
                 context,
                 authority,
                 file
             )
         }



     }

}


