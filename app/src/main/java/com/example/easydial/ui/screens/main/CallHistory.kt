package com.example.easydial.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallMade
import androidx.compose.material.icons.automirrored.filled.CallMissed
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.*

@Composable
fun CallHistory(onNavigateToCallDetails: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Text("Call History", fontWeight = FontWeight.Bold, fontSize = 24.sp, color = NavyPrimary)
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search call history...") },
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
            FilterChip(selected = false, onClick = {}, label = { Text("Incoming") })
            FilterChip(selected = false, onClick = {}, label = { Text("Outgoing") })
            FilterChip(selected = false, onClick = {}, label = { Text("Missed") })
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item { Text("TODAY", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray) }
            items(3) { index ->
                CallHistoryItem(
                    name = if (index == 0) "Sarah Jenkins" else "Marketing Dept",
                    type = if (index == 0) "Mobile" else "Landline",
                    time = "10:45 AM",
                    isMissed = index == 1,
                    onClick = onNavigateToCallDetails
                )
            }
            item { Spacer(modifier = Modifier.height(16.dp)); Text("YESTERDAY", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Gray) }
            items(5) {
                CallHistoryItem(
                    name = "David Grey",
                    type = "Mobile",
                    time = "4:32 PM",
                    isMissed = false,
                    onClick = onNavigateToCallDetails
                )
            }
        }
    }
}

@Composable
fun CallHistoryItem(name: String, type: String, time: String, isMissed: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(40.dp).background(Color(0xFFE0E0E0), CircleShape))
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, fontWeight = FontWeight.Bold, color = if (isMissed) CallRed else Color.Black)
                Text("$type • $time", color = Color.Gray, fontSize = 12.sp)
            }
            Icon(
                imageVector = if (isMissed) Icons.AutoMirrored.Filled.CallMissed else Icons.AutoMirrored.Filled.CallMade,
                contentDescription = null,
                tint = if (isMissed) CallRed else CallGreen,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CallHistoryPreview() {
    EasyDialTheme {
        CallHistory(onNavigateToCallDetails = {})
    }
}
