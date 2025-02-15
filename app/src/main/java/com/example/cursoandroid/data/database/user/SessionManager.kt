package com.example.cursoandroid.data.database.user

import android.content.Context
import com.example.cursoandroid.data.database.recipe.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SessionManager(context: Context) {

    private val userSessionDao = RecipeDatabase.getDatabase(context).sessionDao()

    // Guardar sesión en Room
    suspend fun saveSession(session: Session) {
        withContext(Dispatchers.IO) {
            userSessionDao.insert(session)
        }
    }

    // Obtener el estado de sesión
    suspend fun getSession(): Boolean {
        return withContext(Dispatchers.IO) {
            userSessionDao.findOne()?.isLoggedIn ?: false
        }
    }

    // Cerrar sesión
    suspend fun clearSession() {
        withContext(Dispatchers.IO) {
            userSessionDao.clearSession()
        }
    }
}
