package com.duoc.holazehir.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.duoc.holazehir.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDeprecado() {
    // Scaffold proporciona la estructura visual básica de Material Design (TopBar, Snackbar, etc.)
    Scaffold(
        topBar = {
            // 5. Uso de colores de MaterialTheme para la TopAppBar
            TopAppBar(
                title = {
                    Text(
                        text = "Mi App Kotlin",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->

        // 8. Estado reactivo para el nuevo elemento interactivo (OutlinedTextField)
        var nombreUsuario by remember { mutableStateOf("") }

        // 4 y 6. Columna principal con espaciado uniforme y alineación horizontal
        Column(
            modifier = Modifier
                .padding(innerPadding) // Aplica el padding de Scaffold para no solapar la TopBar
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp), // 4. Espaciado uniforme de 16dp entre hijos
            horizontalAlignment = Alignment.CenterHorizontally // 6. Alineación centrada horizontalmente
        ) {

            // 7. SECCIÓN 1: Mensaje de bienvenida con tipografía de MaterialTheme
            Text(
                text = "¡Bienvenido a la aplicación!",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary // 5. Color dinámico del tema
            )

            // 7. SECCIÓN 2: Entrada de texto (Nuevo elemento visual - Punto 8)
            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Ingresa tu nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // 7. SECCIÓN 3: Botón de acción con estilo y soporte de clic
            Button(
                onClick = { /* Acción al presionar */ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (nombreUsuario.isBlank()) "Presióname" else "¡Hola, $nombreUsuario!",
                    style = MaterialTheme.typography.labelLarge
                )
            }

            // 7. SECCIÓN 4: Tarjeta contenedora con información adicional (Nuevo elemento visual - Punto 8)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Información del Sistema",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Esta vista utiliza la arquitectura MVVM junto a Jetpack Compose y los componentes de Material 3.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // 7. SECCIÓN 5: Imagen con recursos locales
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de la Aplicación",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenDeprecado()
}