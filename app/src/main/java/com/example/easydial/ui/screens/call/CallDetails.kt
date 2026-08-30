package com.example.easydial.ui.screens.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallDetails(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Call Details", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) { Icon(Icons.Default.Edit, contentDescription = "Edit") }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF8F9FA))
        ) {
            // Profile Header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.size(80.dp).background(Color.LightGray, CircleShape))
                Spacer(modifier = Modifier.height(16.dp))
                Text("Eleanor Vance", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("+1 (555) 255-0127 • Mobile", color = Color.Gray)
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    CallDetailAction(Icons.Default.Call, "Call")
                    CallDetailAction(Icons.AutoMirrored.Filled.Message, "Message")
                    CallDetailAction(Icons.Default.Videocam, "Video")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Call History Section
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Call History", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    CallLogItem(Icons.AutoMirrored.Filled.CallMissed, "Missed Call", "Today • 10:45 AM", CallRed)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    CallLogItem(Icons.AutoMirrored.Filled.CallMade, "Outgoing", "Today • 9:32 AM • 12m 45s", CallGreen)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    CallLogItem(Icons.AutoMirrored.Filled.CallReceived, "Incoming", "Yesterday • 4:15 PM • 4m 12s", TealAccent)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                ListItem(
                    headlineContent = { Text("Add to Contacts") },
                    leadingContent = { Icon(Icons.Default.PersonAdd, contentDescription = null) }
                )
                ListItem(
                    headlineContent = { Text("Block Number", color = CallRed) },
                    leadingContent = { Icon(Icons.Default.Block, contentDescription = null, tint = CallRed) }
                )
                ListItem(
                    headlineContent = { Text("Delete Call History", color = CallRed) },
                    leadingContent = { Icon(Icons.Default.Delete, contentDescription = null, tint = CallRed) }
                )
            }
        }
    }
}

@Composable
fun CallDetailAction(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedIconButton(
            onClick = {},
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(icon, contentDescription = label, tint = NavyPrimary)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun CallLogItem(icon: ImageVector, status: String, time: String, tint: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(32.dp).background(tint.copy(alpha = 0.1f), CircleShape), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(status, fontWeight = FontWeight.Medium)
            Text(time, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CallDetailsPreview() {
    EasyDialTheme {
        CallDetails(onBack = {})
    }
}
