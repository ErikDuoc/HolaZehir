package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.viewmodel.MainViewModel

import androidx.compose.ui.tooling.preview.Preview
import com.duoc.holazehir.ui.theme.MiAppTheme

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    ProfileScreenContent(
        onScreenSelected = { screen -> viewModel.navigateTo(screen) }
    )
}

@Composable
fun ProfileScreenContent(
    onScreenSelected: (Screen) -> Unit
) {
    Scaffold(
        bottomBar = {
            AppNavigationBar(
                currentScreen = Screen.Profile,
                onScreenSelected = onScreenSelected,
                items = listOf(Screen.Home, Screen.Profile)
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
            Text(text = "¡Bienvenido al Perfil!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MiAppTheme {
        ProfileScreenContent(
            onScreenSelected = {}
        )
    }
}