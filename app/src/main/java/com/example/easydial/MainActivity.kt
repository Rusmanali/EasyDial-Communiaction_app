package com.example.easydial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.easydial.data.CallStatus
import com.example.easydial.data.PresenceManager
import com.example.easydial.ui.navigation.AppNavigation
import com.example.easydial.ui.screens.call.ActiveCall
import com.example.easydial.ui.screens.call.IncomingCall
import com.example.easydial.ui.theme.EasyDialTheme
import com.example.easydial.viewmodel.CallViewModel

class MainActivity : ComponentActivity() {
    private val presenceManager = PresenceManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyDialTheme {
                val navController = rememberNavController()
                val callViewModel: CallViewModel = viewModel()
                val currentCall by callViewModel.currentCall.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navController = navController)
                    
                    // Global Call Overlay
                    when (currentCall.status) {
                        CallStatus.RINGING -> {
                            IncomingCall(
                                onAccept = { callViewModel.acceptCall() },
                                onDecline = { callViewModel.endCall() }
                            )
                        }
                        CallStatus.CONNECTED -> {
                            ActiveCall(
                                onEndCall = { callViewModel.endCall() }
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenceManager.updatePresence(true)
    }

    override fun onStop() {
        super.onStop()
        presenceManager.updatePresence(false)
    }
}
