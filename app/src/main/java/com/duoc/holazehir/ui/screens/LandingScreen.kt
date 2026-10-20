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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
                onClick = { viewModel.navigateTo(Screen.Registro) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Registrarse",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
}