package com.example.cursoandroid.ui.screens.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import com.example.cursoandroid.data.database.recipe.Recipe
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.ui.components.CustomOutlinedTextField

@Composable
fun RecipeForm() {
    var description by remember { mutableStateOf("") }
    var tittle by remember { mutableStateOf("") }
    var favorite by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    val focusDescription = remember { FocusRequester() }
    val focusTittle = remember { FocusRequester() }
    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Agregar recetas",
                onBackPressed = true
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            CustomOutlinedTextField(
                value = tittle,
                onValueChange = { tittle = it },
                label = "Títuto",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusTittle),
                imeAction = ImeAction.Next,
                onImeAction = {
                    focusTittle.requestFocus()
                }
            )

            CustomOutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = "Descripción",
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusDescription),
                imeAction = ImeAction.Next,
                onImeAction = {
                    focusTittle.requestFocus()
                }
            )

            Button(
                onClick = {
                    if (tittle.isNotEmpty() && description.isNotEmpty()) {
                        val recipe = image.ifEmpty { null }?.let {
                            Recipe(
                                title = tittle,
                                description = description,
                                favorite = favorite,
                                score = 1f,
                                image = it
                            )
                        }
                        if (recipe != null) {

                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Receta")
            }
        }
    }
}
