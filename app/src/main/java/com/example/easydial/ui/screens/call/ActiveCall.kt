package com.example.easydial.ui.screens.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Mic
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
import com.example.easydial.ui.theme.CallRed
import com.example.easydial.ui.theme.EasyDialTheme

@Composable
fun ActiveCall(onEndCall: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF2C3E50)).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(120.dp).background(Color.Gray, CircleShape))
        Spacer(modifier = Modifier.height(24.dp))
        Text("Sarah Jenkins", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("04:12", color = Color.White.copy(alpha = 0.7f), fontSize = 18.sp)
        
        Spacer(modifier = Modifier.height(80.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            CallActionItem(Icons.Default.Mic, "Mute")
            CallActionItem(Icons.AutoMirrored.Filled.VolumeUp, "Speaker")
            CallActionItem(Icons.Default.Dialpad, "Keypad")
        }
        
        Spacer(modifier = Modifier.height(60.dp))
        
        FloatingActionButton(
            onClick = onEndCall,
            containerColor = CallRed,
            shape = CircleShape,
            modifier = Modifier.size(72.dp)
        ) {
            Icon(Icons.Default.CallEnd, contentDescription = "End Call", tint = Color.White, modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun CallActionItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(56.dp)
                .background(Color.White.copy(alpha = 0.2f), CircleShape)
        ) {
            Icon(icon, contentDescription = label, tint = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, color = Color.White, fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ActiveCallPreview() {
    EasyDialTheme {
        ActiveCall(onEndCall = {})
    }
}
