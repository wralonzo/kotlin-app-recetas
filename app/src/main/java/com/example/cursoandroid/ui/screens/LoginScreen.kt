package com.example.cursoandroid.ui.screens


import LocalNavController
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cursoandroid.R
import com.example.cursoandroid.data.database.user.Session
import com.example.cursoandroid.data.database.user.SessionManager
import com.example.cursoandroid.ui.components.CustomOutlinedTextField
import com.example.cursoandroid.ui.components.CustomSnackbarHost
import com.example.cursoandroid.ui.components.CustomSpace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val usernameFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val logo: Painter = painterResource(id = R.drawable.logo)
    val backgroung: Painter = painterResource(id = R.drawable.images)
    var offset by remember { mutableStateOf(0f) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val navController = LocalNavController.current
    BackHandler {
        // No hacer nada para bloquear el retroceso
    }

    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    Scaffold(
        snackbarHost = { CustomSnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .scrollable(
                    orientation = Orientation.Vertical,
                    // Scrollable state: describes how to consume
                    // scrolling delta and update offset
                    state = rememberScrollableState { delta ->
                        offset += delta
                        delta
                    }
                ),
            contentAlignment = Alignment.Center


        ) {
            Image(
                painter = backgroung,
                contentDescription = "Logo",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .windowInsetsPadding(WindowInsets.ime)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = logo,
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(300.dp)
                        .size(100.dp),
                    colorFilter = ColorFilter.tint(
                        Color.White
                    ),
                )


                CustomSpace(height = 20)

                Card(
                    shape = RoundedCornerShape(25.dp),
                    elevation = CardDefaults.elevatedCardElevation(99.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomOutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = "Username",
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(usernameFocusRequester),
                            imeAction = ImeAction.Next,
                            isRequired = true,
                            keyboardType = KeyboardType.Email,
                            onImeAction = {
                                passwordFocusRequester.requestFocus()
                            }
                        )

                        CustomSpace(height = 20)

                        CustomOutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = "Password",
                            isPassword = true,
                            imeAction = ImeAction.Done,
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(passwordFocusRequester),
                            onImeAction = {},
                            isRequired = true,
                        )

                        CustomSpace(height = 20)

                        Button(
                            onClick = {
                                if (username == "info@koalit.dev" && password == "koalit123") {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        sessionManager.saveSession(Session(
                                            isLoggedIn = true,
                                            name = "",
                                            user = username
                                        ))
                                    }
                                    navController.navigate("home")
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = "Bienvenido ${username}",
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                    return@Button
                                }
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Usuario y/o clave incorrecta",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {
                            Text("Login")
                        }
                    }
                }
            }
        }
    }
}
