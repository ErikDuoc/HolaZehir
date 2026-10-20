package com.duoc.holazehir.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.holazehir.viewmodel.EstadoViewModel

@Composable
fun PantallaEstado(
    modifier: Modifier = Modifier,
    viewModel: EstadoViewModel = viewModel()
) {
    val estado by viewModel.activo.collectAsState()
    val mostrarMensaje by viewModel.mostrarMensaje.collectAsState()

    // Parte 1: estado que sobrevive a rotaciones
    var cambios by rememberSaveable { mutableIntStateOf(0) }

    if (estado == null) {
        // Parte 3: loader
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val estaActivo = estado == true

        val colorAnimado by animateColorAsState(
            targetValue = if (estaActivo) Color(0xFF4CAF50) else Color(0xFFB0BEC5),
            animationSpec = tween(durationMillis = 500),
            label = "colorBoton"
        )

        // Parte 1: texto derivado
        val textoBoton by remember(estaActivo) {
            derivedStateOf { if (estaActivo) "Desactivar" else "Activar" }
        }

        // Parte 5: efecto extra (la tarjeta "rebota" al cambiar)
        val escala by animateFloatAsState(
            targetValue = if (estaActivo) 1f else 0.95f,
            animationSpec = spring(dampingRatio = 0.4f),
            label = "escalaTarjeta"
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(escala)
                    .animateContentSize(),
                colors = CardDefaults.cardColors(containerColor = colorAnimado.copy(alpha = 0.25f))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Parte 5: texto que cambia con transición
                    AnimatedContent(targetState = estaActivo, label = "textoModo") { activo ->
                        Text(
                            text = if (activo) "Modo especial ACTIVADO" else "Modo especial DESACTIVADO",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Veces cambiado en esta sesión: $cambios",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.alternarEstado()
                    cambios++
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorAnimado),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text(text = textoBoton, style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Parte 4: mensaje animado
            AnimatedVisibility(visible = mostrarMensaje) {
                Text(
                    text = "¡Estado guardado exitosamente!",
                    color = Color(0xFF4CAF50),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}