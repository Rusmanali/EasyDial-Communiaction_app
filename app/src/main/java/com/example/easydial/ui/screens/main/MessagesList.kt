package com.example.easydial.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.EasyDialTheme
import com.example.easydial.ui.theme.NavyPrimary
import com.example.easydial.ui.theme.TealAccent

@Composable
fun MessagesList(onNavigateToChat: (String) -> Unit) {
    val mockContacts = listOf("Alex Chen", "Sarah Jenkins", "Design Team Alpha", "Marcus Tran")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text("Messages", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = NavyPrimary)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search messages...") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = true, onClick = {}, label = { Text("All") })
            FilterChip(selected = false, onClick = {}, label = { Text("Unread") })
            FilterChip(selected = false, onClick = {}, label = { Text("Personal") })
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(mockContacts) { name ->
                MessageItem(
                    name = name,
                    snippet = "Latest message snippet goes here...",
                    time = "10:45 AM",
                    isOnline = name == "Sarah Jenkins", // Mocking online status
                    onNavigateToChat = onNavigateToChat
                )
            }
        }
    }
}

@Composable
fun MessageItem(name: String, snippet: String, time: String, isOnline: Boolean, onNavigateToChat: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigateToChat(name) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Box(modifier = Modifier.size(50.dp).background(Color.LightGray, CircleShape))
            if (isOnline) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(TealAccent, CircleShape)
                        .align(Alignment.BottomEnd)
                        .offset(x = (-2).dp, y = (-2).dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(name, fontWeight = FontWeight.Bold)
            Text(snippet, color = Color.Gray, fontSize = 14.sp, maxLines = 1)
        }
        Text(time, color = Color.Gray, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun MessagesListPreview() {
    EasyDialTheme {
        MessagesList(onNavigateToChat = {})
    }
}
