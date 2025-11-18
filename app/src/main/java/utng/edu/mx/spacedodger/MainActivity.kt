package utng.edu.mx.spacedodger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import utng.edu.mx.spacedodger.ui.screens.GameScreen
import utng.edu.mx.spacedodger.ui.theme.SpaceDodgerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Forzar orientación horizontal (opcional, ya lo hace el manifest)
        // requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            SpaceDodgerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}