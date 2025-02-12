package com.example.cursoandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursoandroid.data.database.recipe.Recipe
import com.example.cursoandroid.data.database.recipe.RecipeDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val db = RecipeDatabase.getDatabase(application)
    private val recipeDao = db.recipeDao()

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAllRecipes()
    val favoriteRecipes: Flow<List<Recipe>> = recipeDao.getFavoriteRecipes()

    fun insert(recipe: Recipe) = viewModelScope.launch {
        recipeDao.insert(recipe)
    }

    fun update(recipe: Recipe) = viewModelScope.launch {
        recipeDao.update(recipe)
    }

    fun delete(recipe: Recipe) = viewModelScope.launch {
        recipeDao.delete(recipe)
    }
}
