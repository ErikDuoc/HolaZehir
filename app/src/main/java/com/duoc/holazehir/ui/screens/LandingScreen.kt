package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.duoc.holazehir.R
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.viewmodel.MainViewModel

@Composable
fun LandingScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            AppNavigationBar(
                currentScreen = Screen.Landing,
                onScreenSelected = { screen -> viewModel.navigateTo(screen) },
                items = listOf(Screen.Home, Screen.Landing)
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
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo de la Aplicación",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Fit
            )
            Text(text = "¡Bienvenido | Hoş geldin!")
            Button(
                onClick = { viewModel.navigateTo(Screen.Home) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Ingresar ",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}