package com.fatec.composeui.ui.components.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun CustomFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = { onClick() }) {
        Icon(Icons.Filled.Add, "Floating Action Button")
    }
}