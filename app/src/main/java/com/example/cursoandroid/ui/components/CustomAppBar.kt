package com.example.cursoandroid.ui.components

import LocalNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(title: String, onBackPressed: Boolean? = true) {
    val navController = LocalNavController.current
    if (onBackPressed == true) {
        // Si se pasa onBackPressed, usamos TopAppBar con título alineado a la izquierda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF6200EE),
                            Color(0xFF9C27B0)
                        ) // Degradado de púrpura
                    )
                )
        ) {
            CustomAppBarNormal(title = title, onBackPressed = onBackPressed)
        }
    } else {
        // Si no se pasa onBackPressed, usamos CenterAlignedTopAppBar para centrar el título
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF6200EE),
                            Color(0xFF9C27B0)
                        ) // Degradado de púrpura
                    )
                )
        ) { CustomAppBarCenter(title = title, onBackPressed = onBackPressed) }
    }
}
