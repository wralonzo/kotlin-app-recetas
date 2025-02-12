package com.example.cursoandroid.ui.components

import LocalNavController
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
import androidx.compose.material.icons.filled.ExitToApp
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomBottomNavigation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF9C27B0), Color(0xFF673AB7))
                )
            )
        // Background to visualize
    ) {
        BottomBarContent()
    }
}


@Composable
fun BottomBarContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(label = "Recetas", route = "recipe",  Icons.Default.List)
        BottomNavItem(label = "Home", route = "recipe",  Icons.Default.Home)

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Center",
                modifier = Modifier.fillMaxSize(),
                tint = Color(0xFF673AB7)
            )
        }

        BottomNavItem(label = "Salir", route = "recipe", Icons.Default.ExitToApp)
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