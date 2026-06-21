package com.zeno.ai.ui.theme
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = NeonCyan, onPrimary = DarkBackground, secondary = NeonPurple, onSecondary = DarkBackground,
    tertiary = NeonPink, background = DarkBackground, onBackground = DarkOnBackground,
    surface = DarkSurface, onSurface = DarkOnSurface, surfaceVariant = Color(0xFF1A2332),
    onSurfaceVariant = DarkOnSurfaceVariant, error = ErrorColor
)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006B73), onPrimary = Color.White, secondary = Color(0xFF4A1F8A),
    background = Color(0xFFF5F7FA), onBackground = Color(0xFF1A1F2E),
    surface = Color.White, onSurface = Color(0xFF3D4A5C)
)

@Composable
fun ZenoAITheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val systemUiController = rememberSystemUiController()
    SideEffect { systemUiController.setSystemBarsColor(color = colorScheme.background.copy(alpha = 0.85f), darkIcons = !darkTheme) }
    MaterialTheme(colorScheme = colorScheme, content = content)
}
