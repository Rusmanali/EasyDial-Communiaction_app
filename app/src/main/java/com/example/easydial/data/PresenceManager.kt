package com.example.easydial.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue

class PresenceManager(private val db: FirebaseDatabase = FirebaseDatabase.getInstance()) {
    
    private val auth = FirebaseAuth.getInstance()

    fun updatePresence(isOnline: Boolean) {
        val userId = auth.currentUser?.uid ?: return
        val presenceRef = db.getReference("users/$userId/presence")
        
        if (isOnline) {
            presenceRef.setValue("online")
            presenceRef.onDisconnect().setValue(ServerValue.TIMESTAMP) // Set last seen on disconnect
        } else {
            presenceRef.setValue(ServerValue.TIMESTAMP)
        }
    }
}
