package com.example.cursoandroid.ui.screens.recipe

import LocalNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.viewmodels.RecipeViewModel

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
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = recipe.title)
                        Text(text = recipe.description)
                        Text(text = "⭐ ${recipe.score}")
                        Button(onClick = { viewModel.delete(recipe) }) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
