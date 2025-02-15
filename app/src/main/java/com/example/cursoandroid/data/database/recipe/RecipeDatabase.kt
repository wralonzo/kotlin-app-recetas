package com.example.cursoandroid.data.database.recipe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cursoandroid.data.database.user.Session
import com.example.cursoandroid.data.database.user.SessionDao

@Database(entities = [Recipe::class, Session::class], version = 2, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun sessionDao(): SessionDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "app_recipe"
                ).fallbackToDestructiveMigration() // Esto maneja la migración en caso de cambios
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
