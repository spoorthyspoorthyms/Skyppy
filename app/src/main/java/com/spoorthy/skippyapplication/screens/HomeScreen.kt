package com.spoorthy.skippyapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spoorthy.skippyapplication.viewmodel.AuthViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: AuthViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome Home!", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            viewModel.logout()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Logout")
        }
    }
}
