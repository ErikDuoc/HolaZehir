package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            AppNavigationBar(
                currentScreen = Screen.Home,
                onScreenSelected = { screen -> viewModel.navigateTo(screen) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "¡Bienvenido a la Página de Inicio!")
            Spacer(modifier = Modifier.height(height = 16.dp))
            Button(onClick = { viewModel.navigateTo(Screen.Tutorial) }) {
                Text(text = "Ir a aprender")
            }
        }
    }
}
