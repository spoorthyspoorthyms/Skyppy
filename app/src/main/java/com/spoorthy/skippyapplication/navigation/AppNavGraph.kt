package com.spoorthy.skippyapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.spoorthy.skippyapplication.ui.screens.LoginScreen
import com.spoorthy.skippyapplication.ui.screens.SignupScreen
import com.spoorthy.skippyapplication.ui.screens.HomeScreen
import com.spoorthy.skippyapplication.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, viewModel) }
        composable("signup") { SignupScreen(navController, viewModel) }
        composable("home") { HomeScreen(navController, viewModel) }
    }
}
