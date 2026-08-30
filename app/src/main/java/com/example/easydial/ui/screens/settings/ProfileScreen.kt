package com.example.easydial.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.EasyDialTheme
import com.example.easydial.ui.theme.NavyPrimary
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(onNavigateToSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = onNavigateToSettings) { Icon(Icons.Default.Settings, contentDescription = "Settings") }
                IconButton(onClick = {}) { Icon(Icons.Default.Notifications, contentDescription = null) }
            }
            Box(modifier = Modifier.size(100.dp).background(Color.LightGray, CircleShape))
            Spacer(modifier = Modifier.height(16.dp))
            Text("Usman", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("+1 (555) 000-0000", color = Color.Gray)
            Text("usman@example.com", color = Color.Gray, fontSize = 14.sp)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Share Account")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        ProfileSection("ACCOUNT SETTINGS") {
            ProfileOptionItem(Icons.Default.Person, "Account", "Personal details, network")
            ProfileOptionItem(Icons.Default.Notifications, "Notifications", "Language, alerts, badges")
        }
        
        ProfileSection("COMMUNICATION") {
            ProfileOptionItem(Icons.Default.Settings, "Calling Preferences", "Voicemail, forwarding, SIP")
            ProfileOptionItem(Icons.AutoMirrored.Filled.Message, "Messaging Preferences", "Message syncing, notifications")
            ProfileOptionItem(Icons.Default.Block, "Blocked Numbers", "Manage numbers you've blocked")
        }
        
        ProfileSection("APP & SUPPORT") {
            ProfileOptionItem(Icons.Default.Security, "Privacy", "Data usage, cookies")
            ProfileOptionItem(Icons.Default.ColorLens, "Appearance", "Theme, layout, spacing")
            ProfileOptionItem(Icons.AutoMirrored.Filled.Help, "Help & Support", "FAQs, contact us")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        TextButton(
            onClick = {
                FirebaseAuth.getInstance().signOut()
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp).height(56.dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Logout", color = Color.Red, fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Text("Easy Dial v1.0.1", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.Gray, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProfileSection(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.White) {
            Column(content = content)
        }
    }
}

@Composable
fun ProfileOptionItem(icon: ImageVector, title: String, subtitle: String) {
    ListItem(
        modifier = Modifier.clickable { },
        headlineContent = { Text(title, fontWeight = FontWeight.SemiBold) },
        supportingContent = { Text(subtitle, color = Color.Gray, fontSize = 12.sp) },
        leadingContent = {
            Box(modifier = Modifier.size(36.dp).background(NavyPrimary.copy(alpha = 0.1f), RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center) {
                Icon(icon, contentDescription = null, tint = NavyPrimary, modifier = Modifier.size(20.dp))
            }
        },
        trailingContent = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray) }
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    EasyDialTheme {
        ProfileScreen(onNavigateToSettings = {})
    }
}
