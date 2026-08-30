package com.example.easydial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easydial.data.DatabaseRepository
import com.example.easydial.data.Message
import com.example.easydial.data.RecentActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DatabaseRepository = DatabaseRepository()) : ViewModel() {

    private val _recentActivity = MutableStateFlow<List<RecentActivity>>(emptyList())
    val recentActivity: StateFlow<List<RecentActivity>> = _recentActivity.asStateFlow()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    init {
        loadRecentActivity()
    }

    private fun loadRecentActivity() {
        viewModelScope.launch {
            repository.getRecentActivity().collect {
                _recentActivity.value = it
            }
        }
    }

    fun loadMessages(chatId: String) {
        viewModelScope.launch {
            repository.getMessages(chatId).collect {
                _messages.value = it
            }
        }
    }

    fun sendMessage(chatId: String, text: String, senderId: String) {
        viewModelScope.launch {
            val message = Message(
                text = text,
                senderId = senderId,
                timestamp = System.currentTimeMillis()
            )
            repository.sendMessage(chatId, message)
        }
    }
}
