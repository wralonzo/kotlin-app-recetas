package com.example.cursoandroid.data.database.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Update
    suspend fun update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM tbl_recipes ORDER BY title ASC")
    fun getAllRecipes(): kotlinx.coroutines.flow.Flow<List<Recipe>>

    @Query("SELECT * FROM tbl_recipes WHERE favorite = 1")
    fun getFavoriteRecipes(): kotlinx.coroutines.flow.Flow<List<Recipe>>
}
