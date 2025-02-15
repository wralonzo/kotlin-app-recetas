package com.example.cursoandroid.ui.screens.recipe

import LocalNavController
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.ui.components.CustomSpace
import com.example.cursoandroid.viewmodels.RecipeViewModel
import java.io.File

@Composable
fun RecipeViewScreen(recipeId: String = "0", viewModel: RecipeViewModel = viewModel()) {
    val context = LocalContext.current
    val REQUEST_PERMISSION_CODE = 1001
    val navController = LocalNavController.current
    var favorite by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(1f) }

    // Verificar permisos antes de cargar la imagen
    if (ContextCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Si no tenemos permisos, pedimos permiso
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_PERMISSION_CODE
        )
    }


    val recipe by viewModel.recipe.collectAsState()
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    // Obtener el contexto


    // Llamamos a la función findRecipeById para buscar la receta
    LaunchedEffect(recipeId) {
        viewModel.findRecipeById(recipeId.toInt())
    }
    // Cargar la imagen después de que la receta se haya cargado
    LaunchedEffect(recipe) {
        recipe?.image?.let { imagePath ->
            // Si la receta tiene una imagen, la cargamos
            val uri = imagePath.toUri()
            val file = File(recipe?.image)
            imageBitmap = BitmapFactory.decodeFile(file.absolutePath)
            println("imageBitmap")
            println(imageBitmap)
            favorite = recipe?.favorite!!
            score = recipe?.score!!
        }
    }

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Detalle de receta", onBackPressed = true
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp) // Margen interno (espacio dentro del Card)
                ) {

                    // Mostrar la imagen si el Bitmap no es nulo
                    imageBitmap?.let { bitmap ->
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = "Recipe Image",
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )

                    } ?: run {
                        // Mostrar un mensaje o un placeholder si no hay imagen
                        Text("No image found.")
                    }

                    CustomSpace(height = 20)

                    // Mostrar el ID de la receta
                    Text(text = "Titulo: ${recipe?.title}")
                    Text(text = "Descripcion: ${recipe?.description}")
                    Text(text = "Minutos: ${recipe?.time}")// Muestra el valor redondeado

                    recipe?.score?.let {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Calificacion: ${score.toInt()}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )

                            Slider(
                                value = score,
                                onValueChange = { newValue ->
                                    score = newValue
                                    recipe?.score = score
                                    viewModel.update(recipe!!)
                                },
                                valueRange = 1f..5f, // Rango de valores de 1 a 5
                                steps = 3, // Permite solo valores enteros (1, 2, 3, 4, 5)
                                colors = SliderDefaults.colors(
                                    thumbColor = MaterialTheme.colorScheme.primary, // Color del círculo deslizante
                                    activeTrackColor = Color.Green, // Color de la barra activa
                                    inactiveTrackColor = Color.Gray // Color de la barra inactiva
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }

                    CustomSpace(height = 20)
                    Card(
                        shape = RoundedCornerShape(16.dp), // Bordes redondeados
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White, // Color de fondo del Card
                            contentColor = Color.Black    // Color del contenido dentro del Card
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 8.dp // Sombra (altura del Card)
                        ),
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround,
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            if (recipe?.favorite!!) {
                                                recipe?.favorite = false
                                            } else {
                                                recipe?.favorite = true
                                            }
                                            favorite = recipe?.favorite!!
                                            viewModel.update(recipe!!)
                                        }
                                        .size(50.dp),
                                    imageVector = Icons.Outlined.Favorite,

                                    contentDescription = "Favorite",
                                    tint = if (favorite == true) Color(0xFFE91E63) else Color(
                                        0xFF99799E
                                    )
                                )
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            navController.popBackStack()
                                        }
                                        .size(50.dp),

                                    imageVector = Icons.Outlined.ArrowBack,
                                    contentDescription = "Favorite",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.delete(recipe!!)
                                            navController.popBackStack()
                                        }
                                        .size(50.dp),

                                    imageVector = Icons.Outlined.Delete,
                                    contentDescription = "Favorite",
                                    tint = Color(0xFFE91E63)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}