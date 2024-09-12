package com.fatec.composeui.ui.screen

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val chatMessages = remember { mutableStateListOf<ChatMessage>() }
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

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isSent) Arrangement.End else Arrangement.Start
    ) {
        if (message.isSent) {
            Spacer(modifier = Modifier.weight(1f))
        }

        Card(
            modifier = Modifier
                .padding(8.dp)
                .clip(
                    if (message.isSent) RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
                    else RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isSent) Green40 else Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (message.isSent) Color.White else Color.Black,
                textAlign = if (message.isSent) TextAlign.End else TextAlign.Start,
                fontSize = 16.sp
            )
        }

        if (!message.isSent) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun InfoCard(onDismiss: () -> Unit, innerPadding: PaddingValues) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Informação do Usuário", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Nome: John Doe", style = MaterialTheme.typography.bodyLarge)
            Text("Email: john.doe@example.com", style = MaterialTheme.typography.bodyLarge)
            Text("Telefone: +55 11 99999-9999", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onDismiss) {
                Text("Fechar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeUITheme {
        WhatsAppScreen()
    }
}

data class ChatMessage(val text: String, val isSent: Boolean)