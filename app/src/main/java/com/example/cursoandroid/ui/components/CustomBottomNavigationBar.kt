package com.example.cursoandroid.ui.components

import LocalNavController
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomBottomNavigation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.LightGray) // Background to visualize
    ) {
        CustomBottomBarShape(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            color = Brush.horizontalGradient(
                listOf(Color(0xFF673AB7), Color(0xFF9C27B0))
            )
        )
        BottomBarContent()
    }
}

@Composable
fun CustomBottomBarShape(
    modifier: Modifier = Modifier,
    color: Brush
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        val path = Path().apply {
            moveTo(0f, height)

            lineTo(width, height)
            lineTo(width, 0f)
            lineTo(0f, 0f)
            close()
        }

        drawPath(
            path = path,
            brush = color // Asegúrate de que color es un Brush
        )
    }
}

@Composable
fun BottomBarContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomNavItem(label = "Recetas", route = "recipe",  Icons.Default.List)
        BottomNavItem(label = "Home", route = "recipe",  Icons.Default.Home)

        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Center",
                modifier = Modifier.fillMaxSize(),
                tint = Color(0xFF673AB7)
            )
        }

        BottomNavItem(label = "Salir", route = "recipe", Icons.Default.Close)
        BottomNavItem(label = "Perfil", route = "recipe", Icons.Default.Person)
    }
}

@Composable
fun BottomNavItem(label: String, route: String, icon: ImageVector) {
    val navController = LocalNavController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            navController.navigate(route)
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White
        )
        Text(text = label, color = Color.White, fontSize = 12.sp)
    }
}