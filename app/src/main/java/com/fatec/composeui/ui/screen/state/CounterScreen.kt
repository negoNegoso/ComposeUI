package com.fatec.composeui.ui.screen.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Alinha verticalmente ao centro
        horizontalAlignment = Alignment.CenterHorizontally // Alinha horizontalmente ao centro
    ) {
        //Remembering State with remember
        val count = remember { mutableIntStateOf(0) }
        Button(onClick = { count.intValue++ }) {
            Text("Count: ${count.intValue}")
        }

        //--------------------------------------------------
        //Using rememberSaveable for Persistent State

        val countSaveable = rememberSaveable { mutableIntStateOf(0) }
        Button(onClick = { countSaveable.intValue++ }) {
            Text("Count: ${countSaveable.intValue}")
        }

        //--------------------------------------------------------
        //Lifting State (State Hoisting)
        var countLifting by rememberSaveable { mutableIntStateOf(0) }
        Counter(countLifting, onIncrement = { countLifting++ })
    }
}

@Composable
fun Counter(count: Int, onIncrement: () -> Unit) {
    Button(onClick = onIncrement) {
        Text("Count: $count")
    }
}