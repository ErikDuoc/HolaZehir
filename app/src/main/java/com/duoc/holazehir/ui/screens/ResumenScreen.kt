package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.viewmodel.UsuarioViewModel
import com.duoc.holazehir.viewmodel.MainViewModel

@Composable
fun ResumenScreen(
    // Added default parameter values using viewModel() so ResumenScreen can be instantiated without parameters in Preview and other composable contexts
    usuarioViewModel: UsuarioViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel()
) {
    val estado by usuarioViewModel.estado.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Bienvenido ${estado.nombre}",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Tus datos son los siguientes:",
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(height = 12.dp))
        Text(text = "Correo: ${estado.correo}")
        Text(text = "Términos: ${if (estado.aceptaTerminos) "Aceptados" else "No aceptados"}")
        Spacer(modifier = Modifier.height(height = 12.dp))
        Button(
            onClick = { mainViewModel.navigateTo(Screen.Home) },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            Text(
                text = "Continuar",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ResumenScreenPreview() {
    ResumenScreen()
}