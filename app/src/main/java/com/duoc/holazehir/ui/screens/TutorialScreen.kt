package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.duoc.holazehir.R
import com.duoc.holazehir.model.TutorialUiState
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.ui.theme.MiAppTheme
import com.duoc.holazehir.viewmodel.MainViewModel
import com.duoc.holazehir.viewmodel.TutorialViewModel

@Composable
fun TutorialScreen(
    navController: NavController,
    viewModel: MainViewModel,
    tutorialViewModel: TutorialViewModel
) {
    val estado by tutorialViewModel.estado.collectAsState()

    TutorialContent(
        estado = estado,
        onPalabraChange = tutorialViewModel::onPalabraChange,
        onComprobar = { tutorialViewModel.comprobar() },
        onScreenSelected = { screen -> viewModel.navigateTo(screen) }
    )
}

@Composable
fun TutorialContent(
    estado: TutorialUiState,
    onPalabraChange: (String) -> Unit,
    onComprobar: () -> Unit,
    onScreenSelected: (Screen) -> Unit
) {
    Scaffold(
        bottomBar = {
            AppNavigationBar(
                currentScreen = Screen.Tutorial,
                onScreenSelected = onScreenSelected,
                items = listOf(Screen.Home, Screen.Profile)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .padding(all = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Escribe el nombre del objeto",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.auto),
                contentDescription = "Araba",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = estado.palabra,
                onValueChange = onPalabraChange,
                label = { Text("Respuesta") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onComprobar,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                Text(text = "Comprobar", fontSize = 18.sp)
            }

            estado.mensajeFeedback?.let { mensaje ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = mensaje,
                    color = if (estado.esCorrecto == true) Color(0xFF2E7D32) else MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TutorialScreenPreview() {
    MiAppTheme {
        TutorialContent(
            estado = TutorialUiState(palabra = "araba"),
            onPalabraChange = {},
            onComprobar = {},
            onScreenSelected = {}
        )
    }
}
