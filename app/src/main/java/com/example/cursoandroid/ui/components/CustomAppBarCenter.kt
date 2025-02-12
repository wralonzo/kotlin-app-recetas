package com.example.cursoandroid.ui.components

import LocalNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.cursoandroid.ui.theme.IconColor
import com.example.cursoandroid.ui.theme.TextColorAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomAppBarCenter(title: String, onBackPressed: Boolean? = true) {
    // Si no se pasa onBackPressed, usamos CenterAlignedTopAppBar para centrar el título
    val navController = LocalNavController.current
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = TextColorAppBar,
            )
        },
        actions = {
            // Botón de salida
            IconButton(
                onClick = {
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout",
                    tint = IconColor)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
    )
}