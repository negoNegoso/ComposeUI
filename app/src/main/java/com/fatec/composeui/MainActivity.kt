package com.fatec.composeui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fatec.composeui.ui.components.button.CustomElevatedButton
import com.fatec.composeui.ui.components.button.CustomFilledButton
import com.fatec.composeui.ui.components.button.CustomFilledTonalButton
import com.fatec.composeui.ui.components.button.CustomOutlinedButton
import com.fatec.composeui.ui.components.button.CustomTextButton
import com.fatec.composeui.ui.theme.ComposeUITheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUITheme {
//                SmallTopAppBar()
//                CustomCenterAlignedTopAppBar()
//                CustomMediumTopAppBar()
//                CustomLargeTopAppBar()
//                CustomBottomAppBar()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues()),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Button")
                            },
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }

                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding))

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize() // Preenche toda a tela
                        .padding(16.dp), // Adiciona um padding para evitar que os botões fiquem colados nas bordas
                    verticalArrangement = Arrangement.Center, // Alinha verticalmente ao centro
                    horizontalAlignment = Alignment.CenterHorizontally // Alinha horizontalmente ao centro
                ) {
                    CustomFilledButton(onClick = {
                        println("CustomFilledButton")
                    })
                    CustomFilledTonalButton(onClick = {
                        println("CustomFilledTonalButton")
                    })
                    CustomOutlinedButton(onClick = {
                        println("CustomOutlinedButton")
                    })
                    CustomElevatedButton(onClick = {
                        println("CustomElevatedButton")
                    })
                    CustomTextButton(onClick = {
                        println("CustomTextButton")
                    })
                }
            }
        }
    }
}


