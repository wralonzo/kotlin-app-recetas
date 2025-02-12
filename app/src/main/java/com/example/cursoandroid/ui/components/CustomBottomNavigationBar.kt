package com.example.cursoandroid.ui.components

import LocalNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursoandroid.ui.theme.PrimaryDegradate
import com.example.cursoandroid.ui.theme.SecondaryDegradate


@Composable
fun CustomBottomNavigation() {
    BottomBarContent()
}


@Composable
fun BottomBarContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryDegradate,
                        SecondaryDegradate
                    )
                )
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        BottomNavItem(label = "Ajustes", route = "recipe", Icons.Outlined.Settings)
        BottomNavItem(label = "Recetas", route = "recipe", Icons.Outlined.Menu)
        BottomNavItem(label = "Salir", route = "recipe", Icons.Default.ExitToApp)
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