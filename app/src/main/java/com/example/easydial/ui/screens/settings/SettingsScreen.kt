package com.example.easydial.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.CallMerge
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.EasyDialTheme
import com.example.easydial.ui.theme.NavyPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // User Header
            ListItem(
                headlineContent = { Text("Alex Carter", fontWeight = FontWeight.Bold) },
                supportingContent = { Text("+1 (555) 012-3456") },
                leadingContent = { Box(modifier = Modifier.size(48.dp).background(Color.LightGray, CircleShape)) },
                trailingContent = { Button(onClick = {}, shape = RoundedCornerShape(8.dp)) { Text("Edit") } }
            )
            
            HorizontalDivider()
            
            Text("Account", modifier = Modifier.padding(16.dp), color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            SettingsToggleItem(Icons.Default.Person, "Personal Info", null)
            SettingsToggleItem(Icons.Default.DialerSip, "Online Numbers", "2")
            
            HorizontalDivider()
            
            Text("Calling", modifier = Modifier.padding(16.dp), color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            SettingsSwitchItem(Icons.Default.Badge, "Caller ID", "Show name and number", true)
            SettingsToggleItem(Icons.AutoMirrored.Filled.CallMerge, "Call Forwarding", "Off")
            SettingsToggleItem(Icons.Default.Voicemail, "Voicemail", null)
            
            HorizontalDivider()
            
            Text("Messaging", modifier = Modifier.padding(16.dp), color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            SettingsSwitchItem(Icons.Default.Wifi, "SMS over Wi-Fi", null, false)
            SettingsToggleItem(Icons.Default.Notifications, "Message Notifications", null)
            
            HorizontalDivider()
            
            Text("App", modifier = Modifier.padding(16.dp), color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            SettingsSwitchItem(Icons.Default.DarkMode, "Dark Mode", null, false)
            SettingsToggleItem(Icons.Default.Language, "Language", "English")
        }
    }
}

@Composable
fun SettingsToggleItem(icon: ImageVector, title: String, value: String?) {
    ListItem(
        modifier = Modifier.clickable { },
        headlineContent = { Text(title) },
        leadingContent = { Icon(icon, contentDescription = null, tint = NavyPrimary) },
        trailingContent = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (value != null) Text(value, color = Color.Gray, modifier = Modifier.padding(end = 8.dp))
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
            }
        }
    )
}

@Composable
fun SettingsSwitchItem(icon: ImageVector, title: String, subtitle: String?, checked: Boolean) {
    var isChecked by remember { mutableStateOf(checked) }
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = if (subtitle != null) { { Text(subtitle) } } else null,
        leadingContent = { Icon(icon, contentDescription = null, tint = NavyPrimary) },
        trailingContent = {
            Switch(checked = isChecked, onCheckedChange = { isChecked = it })
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    EasyDialTheme {
        SettingsScreen(onBack = {})
    }
}
