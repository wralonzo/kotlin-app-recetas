package com.example.cursoandroid.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomImageButton(@DrawableRes resource: Int) {
    Button(
        onClick = {
                  println("Print action button")
        },
        shape = CircleShape,
        modifier = Modifier.padding(2.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    )
    {
        Image(
            painter = painterResource(id = resource),
            contentDescription = "Botón con imagen",
            modifier = Modifier.size(100.dp).clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )

    }
}
