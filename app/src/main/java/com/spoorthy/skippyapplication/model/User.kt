package com.spoorthy.skippyapplication.model

// Simple user data model for Firestore


data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = ""
)