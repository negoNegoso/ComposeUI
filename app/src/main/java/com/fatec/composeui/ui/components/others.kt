package com.fatec.composeui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fatec.composeui.ui.theme.ComposeUITheme


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeUITheme {
        Greeting("Android")
    }
}

data class Message(
    var message: String = "MessageCard",//Opcional
    var desc: String = "MessageDesc",//Opcional
    var body: String//obriga
)

@Composable
fun MessageCard(
    message: Message,
    isImpar: Boolean
) {
    // Alinha a mensagem inteira à esquerda ou direita
    val alignment = if (isImpar) Arrangement.Start else Arrangement.End
    val color = if (isImpar) Color.Red else Color.Blue
    val messageTipografia =
        if (isImpar) MaterialTheme.typography.titleSmall else MaterialTheme.typography.titleLarge
    val messageDescShape =
        if (isImpar) MaterialTheme.shapes.extraSmall else MaterialTheme.shapes.extraLarge

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color),
        horizontalArrangement = alignment, // Inicialmente coloca a mensagem à esquerda

    ) {

//        Image(
//            painter = painterResource(
//                id = R.mipmap.ic_avatar
//            ),
//            contentDescription = "Imagem do avatar",
//            modifier = Modifier
//                //altera o tamanho da img
//                .size(40.dp)
//                //corta a img em algum formato, Circle,Square...
//                .clip(CircleShape)
//                .border(width = 1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
//        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = message.message,
                color = MaterialTheme.colorScheme.secondary,
                style = messageTipografia
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = messageDescShape, shadowElevation = 2.dp) {
                Text(
                    text = message.desc,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@Composable
fun Conversation(messages: List<Message>, innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        LazyColumn {
            items(messages) { message ->
                // Verifica se o índice da mensagem é ímpar
                val isImpar = messages.indexOf(message) % 2 != 0
                MessageCard(message = message, isImpar = isImpar)
            }
        }
        TextField(value = "Texto", onValueChange = {

        })
    }
}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    Surface() {
        val msg = Message("Mensagem", body = "body")
        MessageCard(message = msg, true)
    }
}


//@Preview
//@Composable
//fun ConversationPreview() {
//    ComposeUITheme {
//        Surface {
//            Conversation(messages = SampleData.conversationSample)
//        }
//    }
//}


object SampleData {
    val conversationSample = listOf(
        Message("Mensagem 1", body = "body 1"),
        Message("Mensagem 2", body = "body 2"),
        Message("Mensagem 3", body = "body 3"),
        Message("Mensagem 4", body = "body 4"),
    )
}