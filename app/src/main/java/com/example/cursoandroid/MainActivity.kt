package com.example.cursoandroid

import LocalNavController
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cursoandroid.data.database.user.SessionManager
import com.example.cursoandroid.ui.screens.HomeScreen
import com.example.cursoandroid.ui.screens.LoginScreen
import com.example.cursoandroid.ui.screens.recipe.RecipeForm
import com.example.cursoandroid.ui.screens.recipe.RecipeScreen
import com.example.cursoandroid.ui.screens.recipe.RecipeViewScreen
import com.example.cursoandroid.ui.screens.user.UserProfileScreen

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val sessionManager = SessionManager(context)
            var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }

            // Asegurarse de obtener el valor de la sesión antes de renderizar la interfaz
            LaunchedEffect(Unit) {
                isLoggedIn = sessionManager.getSession()
            }

            // Solo renderizar la pantalla cuando tengamos el valor de isLoggedIn
            if (isLoggedIn != null) {
                CompositionLocalProvider(LocalNavController provides navController) {
                    // Usar NavHost para gestionar la navegación
                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn == true) "home" else "login",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("home") {
                            HomeScreen()
                        }
                        composable("login") {
                            LoginScreen()
                        }
                        composable("recipe") {
                            RecipeScreen()
                        }
                        composable("addRecipe") {
                            RecipeForm()
                        }
                        composable("profile") {
                            UserProfileScreen()
                        }
                        composable(
                            "viewRecipe/{recipeId}", arguments = listOf(navArgument("recipeId") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            val recipeId = backStackEntry.arguments?.getString("recipeId")
                            RecipeViewScreen(recipeId!!)
                        }
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(progress = 10.1f)
                }
            }
        }
    }
}
