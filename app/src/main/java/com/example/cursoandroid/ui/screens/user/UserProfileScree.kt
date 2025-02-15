package com.example.cursoandroid.ui.screens.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cursoandroid.ui.components.CustomAppBar
import com.example.cursoandroid.ui.components.CustomBottomNavigation
import com.example.cursoandroid.ui.components.CustomSpace
import com.example.cursoandroid.viewmodels.SessionViewModel


@Composable
fun UserProfileScreen(viewModel: SessionViewModel = viewModel()) {
    val sesion by viewModel.session.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.findSessionById()
        println(sesion)
    }
    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Perfil de usuario", onBackPressed = true
            )
        },
        bottomBar = { CustomBottomNavigation()}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {   // Verifica si sesion tiene datos
            sesion?.let {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                      Column( Modifier.fillMaxSize(),
                          verticalArrangement = Arrangement.spacedBy(8.dp),
                          horizontalAlignment = Alignment.CenterHorizontally) {
                          CustomSpace(height = 20)
                          Text("Nombre: Info Koalit")
                          Text("Correo: ${it.user}")
                      }
                    }
                }

            } ?: run {
                Text("No se pudo cargar la sesión")
            }
        }
    }
}