package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun TutorialScreen(
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
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Araba",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Fit
            )
//            OutlinedTextField(
//                value = estado.nombre,
//                onValueChange = viewModel::onNombreChange,
//                label = { Text("Nombre") },
//                isError = estado.errores.nombre != null,
//                supportingText = {
//                    estado.errores.nombre?.let {
//                        Text(text = it, color = MaterialTheme.colorScheme.error)
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            )
            Text(text = "¡Escribe el nombre del objeto!")
            Spacer(modifier = Modifier.height(height = 16.dp))
            Button(onClick = { viewModel.navigateTo(Screen.Settings) }) {
                Text(text = "Comprobar")
            }
        }
    }
}
