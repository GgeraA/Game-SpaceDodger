package utng.edu.mx.spacedodger.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * GameHUD muestra la información del juego en pantalla.
 *
 * HUD significa "Head-Up Display" (Visualización Frontal).
 * Es como el tablero de instrumentos de un avión:
 * muestra información importante sin obstruir la vista.
 *
 * Muestra:
 * - Puntuación actual
 * - Nivel actual
 * - Puntuación más alta
 * - Indicador de pausa
 * - Mensaje de Game Over
 *
 * @param score Puntuación actual
 * @param level Nivel actual
 * @param highScore Puntuación más alta
 * @param isPaused Si el juego está pausado
 * @param isGameOver Si el juego terminó
 * @param onPauseClick Callback cuando se presiona pausa
 * @param onRestartClick Callback cuando se presiona reiniciar
 */
@Composable
fun GameHUD(
    score: Int,
    level: Int,
    highScore: Int,
    isPaused: Boolean,
    isGameOver: Boolean,
    onPauseClick: () -> Unit,
    onRestartClick: () -> Unit
) {
    // Contenedor principal que ocupa toda la pantalla
    // pero no interfiere con los toques (clickable = false por defecto)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // PANEL SUPERIOR: Información del juego
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(16.dp)
        ) {
            // Puntuación actual
            InfoCard(
                label = "PUNTUACIÓN",
                value = score.toString(),
                color = Color(0xFF00CED1) // Cyan
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Información secundaria en fila
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Nivel actual
                InfoCard(
                    label = "NIVEL",
                    value = level.toString(),
                    color = Color(0xFFFFD700), // Dorado
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Récord personal
                InfoCard(
                    label = "RÉCORD",
                    value = highScore.toString(),
                    color = Color(0xFFFF6B35), // Naranja
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // PANEL LATERAL: Botones de control
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Botón de pausa
            ControlButton(
                text = if (isPaused) "▶" else "⏸",
                onClick = onPauseClick,
                backgroundColor = Color(0xFF4CAF50), // Verde
                enabled = !isGameOver
            )

            // Botón de reinicio
            ControlButton(
                text = "↻",
                onClick = onRestartClick,
                backgroundColor = Color(0xFFF44336) // Rojo
            )
        }

        // MENSAJE DE PAUSA
        if (isPaused && !isGameOver) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "⏸ PAUSADO",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Yellow
                    )

                    Text(
                        text = "Toca ▶ para continuar",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }

        // PANTALLA DE GAME OVER
        if (isGameOver) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.85f)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Título GAME OVER
                    Text(
                        text = "💥 GAME OVER",
                        fontSize = 52.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    // Puntuación final
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Puntuación Final",
                            fontSize = 24.sp,
                            color = Color.White
                        )

                        Text(
                            text = score.toString(),
                            fontSize = 64.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00CED1)
                        )
                    }

                    // Mensaje si es nuevo récord
                    if (score == highScore && score > 0) {
                        Text(
                            text = "🏆 ¡NUEVO RÉCORD! 🏆",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFD700)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón de reinicio grande
                    Button(
                        onClick = onRestartClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50)
                        ),
                        modifier = Modifier
                            .width(200.dp)
                            .height(56.dp)
                    ) {
                        Text(
                            text = "JUGAR DE NUEVO",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/**
 * InfoCard muestra una tarjeta de información.
 * Es como un marcador en un tablero deportivo.
 *
 * @param label Etiqueta descriptiva (ej: "PUNTUACIÓN")
 * @param value Valor a mostrar (ej: "1250")
 * @param color Color del valor
 * @param modifier Modificador opcional
 */
@Composable
private fun InfoCard(
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color.Black.copy(alpha = 0.7f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Etiqueta
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f),
            fontWeight = FontWeight.Medium
        )

        // Valor
        Text(
            text = value,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}

/**
 * ControlButton es un botón de control del juego.
 * Es como los botones de un control remoto.
 *
 * @param text Texto/emoji a mostrar
 * @param onClick Acción al presionar
 * @param backgroundColor Color de fondo
 * @param enabled Si el botón está habilitado
 */
@Composable
private fun ControlButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f)
        ),
        modifier = Modifier.size(56.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}