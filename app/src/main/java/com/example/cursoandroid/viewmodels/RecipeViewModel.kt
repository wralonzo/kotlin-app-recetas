package com.example.cursoandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursoandroid.data.database.recipe.Recipe
import com.example.cursoandroid.data.database.recipe.RecipeDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = RecipeDatabase.getDatabase(application)
    private val recipeDao = db.recipeDao()

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()
    val favoriteRecipes: Flow<List<Recipe>> = recipeDao.getFavoriteRecipes()

    // Usamos un MutableLiveData o MutableStateFlow para almacenar el resultado de la búsqueda
    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> get() = _recipe

    // Función para buscar la receta por ID
    fun findRecipeById(id: Int) = viewModelScope.launch {
        // Recolectamos el flujo de resultados de la consulta
        recipeDao.findOne(id).collect { foundRecipe ->
            // Asumimos que `foundRecipe` puede ser null si no se encuentra la receta
            _recipe.value = foundRecipe
        }
    }
    fun insert(recipe: Recipe) = viewModelScope.launch {
        recipeDao.insert(recipe)
        println("Recipe saved")
    }

    fun update(recipe: Recipe) = viewModelScope.launch {
        recipeDao.update(recipe)
    }

    fun delete(recipe: Recipe) = viewModelScope.launch {
        recipeDao.delete(recipe)
    }
}
