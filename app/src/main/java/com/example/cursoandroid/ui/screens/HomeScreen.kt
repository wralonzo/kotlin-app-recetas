package com.example.cursoandroid.ui.screens


import LocalNavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cursoandroid.R
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.ui.components.CustomBottomNavigation
import com.example.cursoandroid.ui.components.CustomSpace

val menuItems = listOf(
    R.drawable.logonegro,
    R.drawable.fondo,
    R.drawable.ic_launcher_background,
)

@Composable
@Preview()
fun HomeScreen() {
    val navController = LocalNavController.current
    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Koalit APP",
                onBackPressed = false
            )
        },
        bottomBar = { CustomBottomNavigation() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            CustomSpace(height = 10)

        }
    }
}