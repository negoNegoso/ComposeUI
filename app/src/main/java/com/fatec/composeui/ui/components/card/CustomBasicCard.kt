package com.fatec.composeui.ui.components.card

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun CustomBasicCard() {
    Card() {
        Text(text = "Card!")
    }
}