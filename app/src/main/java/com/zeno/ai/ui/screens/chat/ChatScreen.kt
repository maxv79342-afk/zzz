package com.zeno.ai.ui.screens.chat
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zeno.ai.domain.model.Message
import com.zeno.ai.domain.model.MessageRole
import com.zeno.ai.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var messages by remember { mutableStateOf(listOf<Message>()) }
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Zeno AI", fontWeight = FontWeight.Bold) }, navigationIcon = { IconButton(onClick = { }) { Icon(Icons.Filled.Menu, contentDescription = "Menu") } }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)) },
        bottomBar = {
            Surface(modifier = Modifier.fillMaxWidth().padding(12.dp), shape = RoundedCornerShape(28.dp), color = MaterialTheme.colorScheme.surfaceVariant) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    TextField(value = text, onValueChange = { text = it }, modifier = Modifier.weight(1f), placeholder = { Text("پیام خود را بنویسید...") }, colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent))
                    IconButton(onClick = { if(text.isNotBlank()) { val userMsg = Message(id = System.currentTimeMillis().toString(), conversationId = "1", role = MessageRole.USER, content = text); val botMsg = Message(id = (System.currentTimeMillis()+1).toString(), conversationId = "1", role = MessageRole.ASSISTANT, content = "پاسخ به: " + text, model = "GLM-5.2"); messages = messages + userMsg + botMsg; text = "" } }) {
                        Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(12.dp)).background(Brush.linearGradient(colors = listOf(GradientStart, GradientEnd))), contentAlignment = Alignment.Center) { Icon(Icons.Filled.Send, contentDescription = "Send", tint = Color.White) }
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding).background(MaterialTheme.colorScheme.background)) {
            if (messages.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("چطور میتونم کمکتون کنم؟", style = MaterialTheme.typography.headlineMedium, color = NeonCyan) }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 8.dp)) {
                    items(messages, key = { it.id }) { message ->
                        val isUser = message.role == MessageRole.USER
                        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp), horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start) {
                            Surface(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = if (isUser) 20.dp else 4.dp, bottomEnd = if (isUser) 4.dp else 20.dp), color = if(isUser) NeonCyan.copy(alpha=0.2f) else MaterialTheme.colorScheme.surfaceVariant) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    if(!isUser) Text(message.model ?: "", style = MaterialTheme.typography.labelSmall, color = NeonCyan)
                                    Text(message.content, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
