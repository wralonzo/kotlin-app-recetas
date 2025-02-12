package com.example.cursoandroid.data.database.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val image: String,
    val favorite: Boolean = false,
    val score: Float = 0f,
    val comments: String = ""
)