package com.example.easydial.ui.screens.call

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.CallGreen
import com.example.easydial.ui.theme.EasyDialTheme
import com.example.easydial.ui.theme.NavyPrimary

@Composable
fun DialPad(onCallStarted: (String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text("Dial Pad", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = NavyPrimary)
        Spacer(modifier = Modifier.height(60.dp))
        
        Text(phoneNumber.ifEmpty { "Enter Number" }, fontSize = 40.sp, fontWeight = FontWeight.Medium, color = if (phoneNumber.isEmpty()) Color.LightGray else Color.Black)
        
        Spacer(modifier = Modifier.height(40.dp))
        
        // Dialer Grid
        val buttons = listOf(
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "*", "0", "#"
        )
        
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            for (i in 0 until 4) {
                Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                    for (j in 0 until 3) {
                        val digit = buttons[i * 3 + j]
                        DialButton(digit, onClick = {
                            if (phoneNumber.length < 15) phoneNumber += digit
                        })
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        FloatingActionButton(
            onClick = { if (phoneNumber.isNotEmpty()) onCallStarted(phoneNumber) },
            containerColor = CallGreen,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier.size(72.dp)
        ) {
            Icon(Icons.Default.Call, contentDescription = "Call", modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun DialButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(Color(0xFFF0F0F0), CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun DialPadPreview() {
    EasyDialTheme {
        DialPad(onCallStarted = {})
    }
}
