package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.ui.theme.MiAppTheme
import com.duoc.holazehir.viewmodel.MainViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import com.duoc.holazehir.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    HomeScreenContent(
        onScreenSelected = { screen -> viewModel.navigateTo(screen) }
    )
}

@Composable
fun HomeScreenContent(
    onScreenSelected: (Screen) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFF853FAC),
        bottomBar = {
            AppNavigationBar(
                currentScreen = Screen.Home,
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
            Text(
                text = "Nivel 1: Objetos en Turco",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(height = 16.dp))
            Image(
                painter = painterResource(id = R.drawable.lesson1),
                contentDescription = "Lección 1",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onScreenSelected(Screen.Tutorial)
                        println("¡Hiciste clic en la imagen!")
                    }
            )
            Spacer(modifier = Modifier.height(height = 16.dp))
            Button(
                onClick = { onScreenSelected(Screen.Tutorial) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 20.dp, horizontal = 120.dp)

            ) {
                Text(
                    text = "Iniciar",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MiAppTheme {
        HomeScreenContent(
            onScreenSelected = {}
        )
    }
}
