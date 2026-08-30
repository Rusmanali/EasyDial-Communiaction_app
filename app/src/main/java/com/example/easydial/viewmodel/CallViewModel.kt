package com.example.easydial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.easydial.data.CallInfo
import com.example.easydial.data.VoIPManager
import kotlinx.coroutines.flow.StateFlow

class CallViewModel(application: Application) : AndroidViewModel(application) {
    private val voipManager = VoIPManager(application)
    
    val currentCall: StateFlow<CallInfo> = voipManager.currentCall

    fun makeCall(number: String) {
        voipManager.makeCall(number)
    }

    fun acceptCall() {
        voipManager.acceptCall()
    }

    fun endCall() {
        voipManager.endCall()
    }
}
