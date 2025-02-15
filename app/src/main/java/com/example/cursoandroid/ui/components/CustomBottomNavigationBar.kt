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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursoandroid.data.database.user.SessionManager
import com.example.cursoandroid.ui.theme.PrimaryDegradate
import com.example.cursoandroid.ui.theme.SecondaryDegradate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

        BottomNavItem(label = "Perfil", route = "profile", Icons.Outlined.Person)
        BottomNavItem(label = "Recetas", route = "recipe", Icons.Outlined.Menu)
        BottomNavItem(
            label = "Salir",
            route = "login",
            Icons.Default.ExitToApp,
            cerrarSesion = true
        )
    }
}

@Composable
fun BottomNavItem(label: String, route: String, icon: ImageVector, cerrarSesion: Boolean = false) {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val sessionManager = SessionManager(context)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            if (cerrarSesion) {
                CoroutineScope(Dispatchers.IO).launch {
                    sessionManager.clearSession()
                }
            }
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