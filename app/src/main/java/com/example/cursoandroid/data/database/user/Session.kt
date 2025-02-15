package com.example.cursoandroid.data.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_sessions")
data class Session(
    @PrimaryKey val id: Int = 1, // Siempre una sola fila
    val isLoggedIn: Boolean,
    val user: String,
    val name: String,
)