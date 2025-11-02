package com.spoorthy.skippyapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.spoorthy.skippyapplication.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String?) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        _authState.value = AuthState.Success(it.user?.uid)
                    }
                    .addOnFailureListener {
                        _authState.value = AuthState.Error(it.message ?: "Signup failed")
                    }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Unexpected error")
            }
        }
    }


    private fun saveUserToFirestore(user: User) {
        firestore.collection("users").document(user.userId)
            .set(user)
            .addOnSuccessListener { println("✅ User saved in Firestore") }
            .addOnFailureListener { e -> println("❌ Error saving user: ${e.message}") }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Success(auth.currentUser?.uid)
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "Login failed")
                    }
                }
        }
    }

    fun logout() {
        auth.signOut()
        _authState.value = AuthState.Idle
    }
}
