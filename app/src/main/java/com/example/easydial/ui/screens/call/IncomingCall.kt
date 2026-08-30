package com.example.easydial.ui.screens.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.theme.CallGreen
import com.example.easydial.ui.theme.CallRed
import com.example.easydial.ui.theme.EasyDialTheme
import com.example.easydial.ui.theme.NavyPrimary

@Composable
fun IncomingCall(onAccept: () -> Unit, onDecline: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(NavyPrimary)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.size(120.dp).background(Color.Gray, CircleShape))
            Spacer(modifier = Modifier.height(24.dp))
            Text("Sarah Jenkins", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text("+1 (555) 555-0127", color = Color.White.copy(alpha = 0.7f), fontSize = 18.sp)
            
            Spacer(modifier = Modifier.height(100.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(64.dp)) {
                FloatingActionButton(
                    onClick = onDecline,
                    containerColor = CallRed,
                    shape = CircleShape,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(Icons.Default.CallEnd, contentDescription = "Decline", tint = Color.White)
                }
                FloatingActionButton(
                    onClick = onAccept,
                    containerColor = CallGreen,
                    shape = CircleShape,
                    modifier = Modifier.size(64.dp)
                ) {
                    Icon(Icons.Default.Call, contentDescription = "Accept", tint = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IncomingCallPreview() {
    EasyDialTheme {
        IncomingCall(onAccept = {}, onDecline = {})
    }
}
