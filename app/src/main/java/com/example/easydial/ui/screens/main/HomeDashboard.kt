package com.example.easydial.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easydial.ui.screens.common.QuickActionItem
import com.example.easydial.ui.screens.common.StatItem
import com.example.easydial.ui.theme.*
import com.example.easydial.viewmodel.MainViewModel

@Composable
fun HomeDashboard(onNavigateToProfile: () -> Unit, viewModel: MainViewModel) {
    val recentActivity by viewModel.recentActivity.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Good morning,", fontSize = 14.sp, color = Color.Gray)
                        Text(
                            "Usman",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = NavyPrimary
                        )
                    }
                }
                Box {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        tint = NavyPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color.Red, CircleShape)
                            .align(Alignment.TopEnd)
                            .offset(x = (-2).dp, y = 2.dp)
                    )
                }
            }
        }

        // Quick Actions
        item {
            Column {
                Text(
                    "Quick Actions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = NavyPrimary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    QuickActionItem(
                        Icons.Default.Call,
                        "Make a Call",
                        Modifier.weight(1f),
                        iconBgColor = TealAccent.copy(alpha = 0.3f),
                        iconTintColor = TealAccent
                    )
                    QuickActionItem(
                        Icons.Default.History,
                        "Recent Calls",
                        Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    QuickActionItem(
                        Icons.AutoMirrored.Filled.Message,
                        "Messages",
                        Modifier.weight(1f),
                        badge = "7"
                    )
                    QuickActionItem(
                        Icons.Default.Contacts,
                        "Contacts",
                        Modifier.weight(1f)
                    )
                }
            }
        }

        // Stats Card
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(androidx.compose.foundation.BorderStroke(1.dp, NavyPrimary.copy(alpha = 0.2f)), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "THIS WEEK",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatItem("124", "Total", NavyPrimary)
                        StatItem("42", "Incoming", CallGreen)
                        StatItem("78", "Outgoing", TealAccent)
                        StatItem("4", "Missed", CallRed)
                    }
                }
            }
        }

        // Recent Activity
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Recent Activity",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = NavyPrimary
                )
                Text(
                    "View All",
                    color = NavyPrimary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { }
                )
            }
        }

        if (recentActivity.isEmpty()) {
            item {
                Text("No recent activity", modifier = Modifier.fillMaxWidth(), textAlign = androidx.compose.ui.text.style.TextAlign.Center, color = Color.Gray)
            }
        } else {
            items(recentActivity) { activity ->
                RecentActivityItem(activity.name, activity.type, activity.time, activity.isMissed)
            }
        }
        
        item { Spacer(modifier = Modifier.height(80.dp)) }
    }
}

@Composable
fun RecentActivityItem(name: String, type: String = "Mobile", time: String = "2 mins ago", isMissed: Boolean = false) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        Column {
            Row(
                modifier = Modifier.padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFE0E0E0), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(name.take(1), fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        name,
                        fontWeight = FontWeight.Bold,
                        color = if (isMissed) CallRed else Color.Black
                    )
                    Text("$type • $time", color = Color.Gray, fontSize = 12.sp)
                }
                Icon(
                    Icons.Default.Call,
                    contentDescription = null,
                    tint = NavyPrimary,
                    modifier = Modifier.size(24.dp)
                )
            }
            HorizontalDivider(color = Color(0xFFF0F0F0))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeDashboardPreview() {
    val mockViewModel = MainViewModel()
    EasyDialTheme {
        HomeDashboard(onNavigateToProfile = {}, viewModel = mockViewModel)
    }
}
