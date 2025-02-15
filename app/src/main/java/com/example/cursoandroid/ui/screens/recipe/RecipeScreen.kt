package com.example.cursoandroid.ui.screens.recipe

import LocalNavController
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.ui.components.CustomSpace
import com.example.cursoandroid.viewmodels.RecipeViewModel
import java.io.File

@Composable
fun RecipeScreen(viewModel: RecipeViewModel = viewModel()) {
    val recipes by viewModel.allRecipes.collectAsState(initial = emptyList())
    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Listado de recetas",
                onBackPressed = true
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("addRecipe")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Receta")
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            recipes.forEach { recipe ->
                var scoreApp = "⭐"
                if (recipe.score == 2f) {
                    scoreApp = "⭐⭐"
                } else if (recipe.score == 3f) {
                    scoreApp = "⭐⭐⭐"
                } else if (recipe.score == 4f) {
                    scoreApp = "⭐⭐⭐⭐"
                } else if (recipe.score == 5f) {
                    scoreApp = "⭐⭐⭐⭐⭐"
                }
                var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
                val file = File(recipe?.image)
                imageBitmap = BitmapFactory.decodeFile(file.absolutePath)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("viewRecipe/${recipe.id}")
                        }
                ) {
                    Row(

                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Título: " + recipe.title)
                            Text(text = "Descripción: " + recipe.description)
                            Text(text = "Tiempo en minutos: " + recipe.time.toString())
                            // Use the ImagePicker component to select an image
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = scoreApp)
                                Icon(
                                    modifier = Modifier
                                        .size(50.dp),
                                    imageVector = Icons.Outlined.Favorite,
                                    contentDescription = "Favorite",
                                    tint = if (recipe.favorite) Color(0xFFE91E63) else Color(
                                        0xFF99799E
                                    )
                                )
                            }

                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            CustomSpace(height = 20)
                            imageBitmap?.let { bitmap ->
                                CustomSpace(height = 20)
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = "Recipe Image",
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .height(50.dp)
                                        .width(50.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            CustomSpace(width = 20)
                        }
                    }
                }
            }
        }
    }
}
