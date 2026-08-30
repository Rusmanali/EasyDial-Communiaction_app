package com.example.easydial.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Calls : BottomNavItem("calls", Icons.Default.Call, "Calls")
    object Dial : BottomNavItem("dial", Icons.Default.Dialpad, "Dial")
    object Messages : BottomNavItem("messages", Icons.AutoMirrored.Filled.Message, "Messages")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}
