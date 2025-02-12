package com.example.cursoandroid

import LocalNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cursoandroid.ui.screens.HomeScreen
import com.example.cursoandroid.ui.screens.LoginScreen
import com.example.cursoandroid.ui.screens.recipe.RecipeForm
import com.example.cursoandroid.ui.screens.recipe.RecipeScreen

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
                // Ahora pasamos navController directamente al NavHost
            CompositionLocalProvider(LocalNavController provides navController) {
                // Usar NavHost para gestionar la navegación
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("home") {
                        HomeScreen()
                    }
                    composable("login") {
                        LoginScreen()
                    }
                    composable("recipe"){
                        RecipeScreen()
                    }
                    composable("addRecipe"){
                        RecipeForm()
                    }
                }
            }

        }
    }
}

