package com.zeno.ai.ui.screens.login
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zeno.ai.ui.theme.*

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(80.dp).clip(RoundedCornerShape(24.dp)).background(Brush.linearGradient(colors = listOf(GradientStart, GradientEnd))), contentAlignment = Alignment.Center) {
                Text("Z", style = MaterialTheme.typography.displayLarge, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("ورود به Zeno AI", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(40.dp))
            Button(onClick = onLoginSuccess, modifier = Modifier.padding(16.dp).fillMaxWidth().height(50.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = NeonCyan)) {
                Text("ورود سریع (تست)", color = DarkBackground, fontWeight = FontWeight.Bold)
            }
        }
    }
}
