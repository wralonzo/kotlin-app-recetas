package com.example.cursoandroid.ui.screens.recipe

import LocalNavController
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursoandroid.data.database.recipe.Recipe
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.ui.components.CustomImagePicker
import com.example.cursoandroid.ui.components.CustomOutlinedTextField
import com.example.cursoandroid.ui.components.CustomSnackbarHost
import com.example.cursoandroid.viewmodels.RecipeViewModel
import kotlinx.coroutines.launch

@Composable
fun RecipeForm() {
    var description by remember { mutableStateOf("") }
    var tittle by remember { mutableStateOf("") }
    var favorite by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf("") }

    val focusDescription = remember { FocusRequester() }
    val focusTittle = remember { FocusRequester() }
    val focusTime = remember { FocusRequester() }

    val viewModel: RecipeViewModel = viewModel()
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Agregar recetas",
                onBackPressed = true
            )
        },
        snackbarHost = { CustomSnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
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
                            focusTime.requestFocus()
                        },
                        isRequired = true
                    )

                    CustomOutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        label = "Tiempo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusTime),
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                        onImeAction = {
                            focusDescription.requestFocus()
                        }
                    )

                    CustomOutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = "Descripción",
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusDescription),
                        imeAction = ImeAction.Done,
                        onImeAction = {
                        },
                        maxLines = 3
                    )

                    CustomImagePicker(
                        context = LocalContext.current,
                        onImageSelected = { uri ->
                            selectedImageUri = uri
                        }
                    )


                    // Use the ImagePicker component to select an image
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Favorito")
                        Icon(
                            modifier = Modifier
                                .size(50.dp)
                                .clickable {
                                    favorite = !favorite
                                },
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Favorite",
                            tint = if (favorite) Color(0xFFE91E63) else Color(0xFF99799E)
                        )
                    }

                    Button(
                        onClick = {
                            if (time.toIntOrNull() == null) {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Ingresa un numero válido",
                                        actionLabel = "OK",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                return@Button
                            }
                            if (tittle.isNotEmpty() && description.isNotEmpty()) {
                                val recipe = Recipe(
                                    title = tittle,
                                    description = description,
                                    favorite = favorite,
                                    score = 1f,
                                    time = time.toInt(),
                                    image = selectedImageUri.toString()
                                )
                                viewModel.insert(recipe)
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Registro almacenado correctamente",
                                        actionLabel = "OK",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text("Guardar Receta")
                    }
                }
            }
        }
    }
}
