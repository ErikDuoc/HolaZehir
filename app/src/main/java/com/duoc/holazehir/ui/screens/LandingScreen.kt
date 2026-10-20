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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.duoc.holazehir.R
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.ui.theme.HomeScreenDeprecado
import com.duoc.holazehir.viewmodel.MainViewModel

@Composable
fun LandingScreen(
    navController: NavController = rememberNavController(),
    viewModel: MainViewModel = viewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Image(
                painter = painterResource(id = R.drawable.holasehir_logo),
                contentDescription = "Logo de HolaZehir",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = "¡Bienvenido | Hoş geldin!",
                modifier = Modifier.padding(10.dp),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { viewModel.navigateTo(Screen.Registro) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.fillMaxWidth().padding(30.dp),
                contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                Text(
                    text = "Registrarse",
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 18.sp
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    LandingScreen()
}