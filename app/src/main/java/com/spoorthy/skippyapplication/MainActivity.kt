package com.spoorthy.skippyapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.spoorthy.skippyapplication.navigation.AppNavGraph
import com.spoorthy.skippyapplication.ui.theme.SkippyApplicationTheme
import com.spoorthy.skippyapplication.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SkippyApplicationTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()
                    AppNavGraph(navController = navController, viewModel = authViewModel)
                }
            }
        }
    }
}
