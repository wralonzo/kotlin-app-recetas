package com.example.cursoandroid.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable()
fun CustomSpace(height: Int = 10, width: Int = 10) {
    Spacer(modifier = Modifier
        .height(height.dp)
        .width(width.dp)
    )
}