package com.zeno.ai.ui.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zeno.ai.ui.screens.login.LoginScreen
import com.zeno.ai.ui.screens.chat.ChatScreen

@Composable
fun ZenoNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(onLoginSuccess = { navController.navigate("chat") { popUpTo("login") { inclusive = true } } }) }
        composable("chat") { ChatScreen() }
    }
}
