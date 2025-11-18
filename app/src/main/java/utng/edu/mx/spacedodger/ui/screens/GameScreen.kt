package utng.edu.mx.spacedodger.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import utng.edu.mx.spacedodger.ui.components.SpaceshipView
import utng.edu.mx.spacedodger.ui.components.StarBackground
import utng.edu.mx.spacedodger.ui.components.AsteroidView
import utng.edu.mx.spacedodger.ui.components.GameHUD
import utng.edu.mx.spacedodger.viewmodel.GameViewModel

@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {
    // Agregar manejo seguro del estado
    val gameState by viewModel.gameState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    try {
                        val normalizedX = change.position.x / size.width
                        viewModel.movePlayer(normalizedX)
                        change.consume()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    ) {
        // Fondo seguro
        StarBackground()

        // Asteroides con manejo seguro
        gameState.asteroids.forEach { asteroid ->
            AsteroidView(asteroid = asteroid)
        }

        // Nave del jugador
        SpaceshipView(
            x = gameState.playerX,
            y = gameState.playerY
        )

        // HUD
        GameHUD(
            score = gameState.score,
            level = gameState.level,
            highScore = gameState.highScore,
            isPaused = gameState.isPaused,
            isGameOver = gameState.isGameOver,
            onPauseClick = { viewModel.togglePause() },
            onRestartClick = { viewModel.restartGame() }
        )
    }
}