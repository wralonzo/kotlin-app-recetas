package com.example.cursoandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cursoandroid.data.database.recipe.RecipeDatabase
import com.example.cursoandroid.data.database.user.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SessionViewModel(application: Application) : AndroidViewModel(application) {
    private val db = RecipeDatabase.getDatabase(application)
    private val sessionDao = db.sessionDao()
    private val _session = MutableStateFlow<Session?>(null)
    val session: StateFlow<Session?> get() = _session
    fun findSessionById() = viewModelScope.launch {
        sessionDao.findOneUser().collect { found ->
            _session.value = found
        }
    }
}
