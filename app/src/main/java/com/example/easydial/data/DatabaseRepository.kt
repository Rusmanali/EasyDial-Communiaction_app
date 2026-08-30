package com.example.easydial.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

data class Message(
    val id: String = "",
    val senderId: String = "",
    val text: String = "",
    val timestamp: Long = 0
)

data class RecentActivity(
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val time: String = "",
    val isMissed: Boolean = false
)

class DatabaseRepository(private val db: FirebaseDatabase = FirebaseDatabase.getInstance()) {

    fun getRecentActivity(): Flow<List<RecentActivity>> = callbackFlow {
        val ref = db.getReference("recent_activity")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(RecentActivity::class.java) }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    fun getMessages(chatId: String): Flow<List<Message>> = callbackFlow {
        val ref = db.getReference("messages").child(chatId)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(Message::class.java) }
                trySend(list)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

    suspend fun sendMessage(chatId: String, message: Message) {
        val ref = db.getReference("messages").child(chatId).push()
        ref.setValue(message.copy(id = ref.key ?: ""))
    }
}
