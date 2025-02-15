package com.example.cursoandroid.ui.components

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun CustomImagePicker(
    context: Context,
    onImageSelected: (Uri?) -> Unit // Aquí el callback sigue permitiendo recibir nulos
) {
    var imageUriState by remember { mutableStateOf<Uri?>(null) }
    var imagePathState by remember { mutableStateOf<String?>(null) }

    // For Image Picking from gallery
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            imageUriState = uri
            imagePathState = getRealPathFromUri(context, uri)  // Convertir la URI en el path
            onImageSelected(uri)  // Notificar al caller con la URI
            print(uri)
            imagePathState = copyImageToInternalStorage(context, it)
            onImageSelected(imagePathState?.toUri())  // Notificar al caller con la URI
            print(uri)
        }
    }

    // Buttons to choose the image source
    Column(
        horizontalAlignment = Alignment.Start
    ) {

        // If an image is selected, display it; otherwise, show a placeholder text
        if (imageUriState != null) {
            imageUriState?.let {
                CustomSpace()
                Image(
                    bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it).asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp),
                    contentScale = ContentScale.Crop
                )

                // Mostrar el path de la imagen seleccionada
                imagePathState?.let { path ->
                    Text(text = "Path: $path")
                }
            }
        }

        // Button to pick an image from the gallery
        Button(onClick = {
            pickImage.launch("image/*")
        }) {
            Text("Seleccionar Imagen")
        }
    }
}

// Función para obtener el path desde la URI
fun getRealPathFromUri(context: Context, uri: Uri): String? {
    if (uri.scheme.equals("content", ignoreCase = true)) {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            if (columnIndex != -1) {
                it.moveToFirst()
                return it.getString(columnIndex) // Ruta completa del archivo
            }
        }
    } else if (uri.scheme.equals("file", ignoreCase = true)) {
        return uri.path // Para URIs de tipo "file", devuelve el path
    }
    return null
}


//Funcion que crea una image local
fun copyImageToInternalStorage(context: Context, uri: Uri): String? {
    val inputStream = context.contentResolver.openInputStream(uri)
    val fileName = "image_${System.currentTimeMillis()}.jpg"
    val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

    inputStream?.copyTo(outputStream)
    inputStream?.close()
    outputStream.close()

    return context.filesDir.absolutePath + "/" + fileName
}