package com.example.easydial.data

import android.content.Context
import com.telnyx.webrtc.sdk.TelnyxClient
import com.telnyx.webrtc.sdk.model.CredentialConfig
import com.telnyx.webrtc.sdk.model.SocketMethod
import com.telnyx.webrtc.sdk.model.SocketResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

enum class CallStatus {
    IDLE, RINGING, CONNECTED, ENDED
}

data class CallInfo(
    val status: CallStatus = CallStatus.IDLE,
    val callerName: String = "",
    val callerNumber: String = "",
    val callId: UUID? = null
)

class VoIPManager(private val context: Context) {
    private val telnyxClient = TelnyxClient(context)
    
    private val _currentCall = MutableStateFlow(CallInfo())
    val currentCall: StateFlow<CallInfo> = _currentCall

    init {
        setupListeners()
    }

    fun login(username: String, password: String) {
        val config = CredentialConfig(username, password, "EasyDial User", "")
        telnyxClient.connect()
        telnyxClient.credentialLogin(config)
    }

    private fun setupListeners() {
        telnyxClient.getSocketResponse()?.observeForever { response: SocketResponse ->
            when (response.method) {
                SocketMethod.INVITE.methodName -> {
                    // Incoming call
                    _currentCall.value = CallInfo(
                        status = CallStatus.RINGING,
                        callerName = "Incoming...",
                        callId = UUID.randomUUID() // Placeholder
                    )
                }
                SocketMethod.ANSWER.methodName -> {
                    _currentCall.value = _currentCall.value.copy(status = CallStatus.CONNECTED)
                }
                SocketMethod.BYE.methodName -> {
                    _currentCall.value = CallInfo(status = CallStatus.ENDED)
                }
            }
        }
    }

    fun makeCall(destinationNumber: String) {
        telnyxClient.call.newInvite(
            "EasyDial User",
            "+10000000000", // Your Telnyx Number
            destinationNumber,
            UUID.randomUUID().toString()
        )
        _currentCall.value = CallInfo(
            status = CallStatus.RINGING,
            callerName = "Calling...",
            callerNumber = destinationNumber
        )
    }

    fun acceptCall() {
        _currentCall.value.callId?.let {
            telnyxClient.acceptCall(it, "EasyDial User", "+10000000000")
        }
    }

    fun endCall() {
        telnyxClient.call.endCall(_currentCall.value.callId ?: UUID.randomUUID())
        _currentCall.value = CallInfo(status = CallStatus.ENDED)
    }
}
