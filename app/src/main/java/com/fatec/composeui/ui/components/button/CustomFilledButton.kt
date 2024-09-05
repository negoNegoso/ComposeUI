package com.fatec.composeui.ui.components.button

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomFilledButton(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Filled")
    }
}