package com.teamneards.classtrack.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn

    init {
        // Check if user is already logged in
        _isUserLoggedIn.value = auth.currentUser != null
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _isUserLoggedIn.value = true
                        onResult(true)
                    } else {
                        Log.e("Auth", "Login failed: ${task.exception?.message}")
                        onResult(false)
                    }
                }
        }
    }

    fun signupUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _isUserLoggedIn.value = true
                        onResult(true)
                    } else {
                        Log.e("Auth", "Signup failed: ${task.exception?.message}")
                        onResult(false)
                    }
                }
        }
    }

    fun logoutUser() {
        auth.signOut()
        _isUserLoggedIn.value = false
    }
}
