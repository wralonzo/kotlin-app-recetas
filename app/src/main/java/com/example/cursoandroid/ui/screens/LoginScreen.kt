package com.example.cursoandroid.ui.screens


import LocalNavController
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cursoandroid.R
import com.example.cursoandroid.ui.components.CustomOutlinedTextField
import com.example.cursoandroid.ui.components.CustomSpace

@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val usernameFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val logo: Painter = painterResource(id = R.drawable.logo)
    val backgroung: Painter = painterResource(id = R.drawable.images)
    var offset by remember { mutableStateOf(0f) }

    val navController = LocalNavController.current
    BackHandler {
        // No hacer nada para bloquear el retroceso
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                        onImeAction = {}
                    )

                    CustomSpace(height = 20)

                    Button(
                        onClick = {
                            if (username == "u" && password == "p") {
                                navController.navigate("home")
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