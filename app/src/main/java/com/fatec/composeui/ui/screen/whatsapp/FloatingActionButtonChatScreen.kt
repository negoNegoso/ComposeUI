package com.fatec.composeui.ui.screen.whatsapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun FloatingActionButtonChatScreen() {
    var showInfo by remember { mutableStateOf(false) }
    val chatMessages = remember { mutableStateListOf<ChatMessage>() }
    var messageText by remember { mutableStateOf("") }
    var currentUser by remember { mutableStateOf(1) }

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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (messageText.isNotBlank()) {
                        chatMessages.add(ChatMessage(messageText, isSent = true, user = currentUser))
                        messageText = ""
                        currentUser = if (currentUser == 1) 2 else 1
                    }
                },
                containerColor = Green40,
                modifier = Modifier.padding(bottom = 50.dp, start = 30.dp)
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        },
        content = { innerPadding ->
            if (showInfo) {
                InfoCard(onDismiss = { showInfo = false }, innerPadding)
            } else {
                WhatsAppChat(
                    chatMessages = chatMessages,
                    messageText = messageText,
                    currentUser = currentUser,
                    onMessageChange = { messageText = it },
                    onSendClick = {
                        if (messageText.isNotBlank()) {
                            chatMessages.add(ChatMessage(messageText, isSent = true, user = currentUser))
                            messageText = ""
                            currentUser = if (currentUser == 1) 2 else 1
                        }
                    },
                    innerPadding = innerPadding
                )
            }
        }
    )
}

@Composable
fun WhatsAppChat(
    chatMessages: MutableList<ChatMessage>,
    messageText: String,
    currentUser: Int,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    innerPadding: PaddingValues
) {
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

        // Layout para a área de entrada de mensagem com o botão
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = onMessageChange,
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

            // FloatingActionButton dentro do Row
            FloatingActionButton(
                onClick = onSendClick,
                containerColor = Green40,
                modifier = Modifier.padding(start = 8.dp) // Adiciona margem esquerda
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun FloatingActionButtonChatScreenPreview() {
    ComposeUITheme {
        FloatingActionButtonChatScreen()
    }
}

