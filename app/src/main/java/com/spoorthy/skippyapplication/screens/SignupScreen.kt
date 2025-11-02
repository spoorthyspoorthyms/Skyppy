package com.spoorthy.skippyapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spoorthy.skippyapplication.viewmodel.AuthState
import com.spoorthy.skippyapplication.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController: NavController, viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState by viewModel.authState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Create a Mood Journal Account", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(onClick = { viewModel.signup(email, password) }) {
                Text("Sign Up")
            }

            TextButton(onClick = { navController.navigate("login") }) {
                Text("Already have an account? Login")
            }

            when (authState) {
                is AuthState.Loading -> CircularProgressIndicator()
                is AuthState.Error -> Text(
                    text = (authState as AuthState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
                is AuthState.Success -> {
                    navController.navigate("home") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
                else -> {}
            }
        }
    }
}
