package com.fatec.composeui.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PageCard() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),

        topBar = {
            TopAppBar(
                title = {
                    Text("Page Card")
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }

    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        Column(
            modifier = Modifier
                .fillMaxSize() // Preenche toda a tela
                .padding(innerPadding), // Adiciona um padding para evitar que os botões fiquem colados nas bordas
            verticalArrangement = Arrangement.spacedBy(16.dp), // Alinha verticalmente ao centro
            horizontalAlignment = Alignment.CenterHorizontally, // Alinha horizontalmente ao centro
        ) {
            CustomBasicCard()
            CustomFilledCard()
            CustomElevatedCard()
            CustomOutlinedCard()
            CustomCompleteCard()
        }
    }
}