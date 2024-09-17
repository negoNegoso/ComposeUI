package com.fatec.composeui.ui.screen.whatsapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatec.composeui.ui.screen.whatsapp.model.ChatMessage
import com.fatec.composeui.ui.screen.whatsapp.ui.components.ChatMessageItem
import com.fatec.composeui.ui.screen.whatsapp.ui.components.InfoCard
import com.fatec.composeui.ui.theme.ComposeUITheme
import com.fatec.composeui.ui.theme.Green40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppScreen() {
    var showInfo by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("John Doe") },
                actions = {
                    IconButton(onClick = { showInfo = true }) {
                        Icon(Icons.Filled.Info, contentDescription = "Info")
                    }
                    IconButton(onClick = { /* Action for MoreVert icon */ }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More")
                    }
                }
            )
        },
        content = { innerPadding ->
            if (showInfo) {
                InfoCard(onDismiss = { showInfo = false }, innerPadding)
            } else {
                WhatsAppChat(innerPadding)
            }
        }
    )
}

@Composable
fun WhatsAppChat(innerPadding: PaddingValues) {
    //= é usado para atribuir um objeto mutableStateListOf à chatMessages, permitindo
    // que você adicione ou remova mensagens da lista.
    val chatMessages = remember { mutableStateListOf<ChatMessage>() }

    //by é usado para delegar a propriedade messageText para um objeto mutableStateOf,
    // que é gerenciado pelo remember. Isso permite que o estado da messageText seja observado
    // e re-renderize a interface do usuário quando houver alterações.
    var messageText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatMessages) { message ->
                ChatMessageItem(message)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Type a message") },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                )
            )

            IconButton(onClick = {
                if (messageText.isNotBlank()) {
                    chatMessages.add(ChatMessage(messageText, isSent = true))
                    messageText = "" // Clear the input field
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send message",
                    tint = Green40
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WhatsAppScreenPreview() {
    ComposeUITheme {
        WhatsAppScreen()
    }
}
