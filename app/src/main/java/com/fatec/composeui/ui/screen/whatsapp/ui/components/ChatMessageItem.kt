package com.fatec.composeui.ui.screen.whatsapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fatec.composeui.ui.screen.whatsapp.model.ChatMessage
import com.fatec.composeui.ui.theme.Green40

@Composable
fun ChatMessageItem(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.user == 1) Arrangement.Start else Arrangement.End
    ) {
        if (message.user == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }

        Card(
            modifier = Modifier
                .padding(8.dp)
                .clip(
                    if (message.user == 1) RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
                    else RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isSent && message.user == 1) Green40 else Color.LightGray
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 2.dp
            )
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = if (message.isSent && message.user == 1) Color.White else Color.Black,
                textAlign = if (message.user == 1) TextAlign.Start else TextAlign.End,
                fontSize = 16.sp
            )
        }

        if (message.user != 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}