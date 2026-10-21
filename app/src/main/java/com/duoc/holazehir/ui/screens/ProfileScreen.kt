package com.duoc.holazehir.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.duoc.holazehir.data.UserData
import com.duoc.holazehir.navigation.Screen
import com.duoc.holazehir.ui.components.AppNavigationBar
import com.duoc.holazehir.viewmodel.MainViewModel
import com.duoc.holazehir.viewmodel.UsuarioViewModel

import androidx.compose.ui.tooling.preview.Preview
import com.duoc.holazehir.ui.theme.MiAppTheme

@Suppress("UNUSED_PARAMETER")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: MainViewModel,
    usuarioViewModel: UsuarioViewModel = viewModel(),
) {
    val userData by usuarioViewModel.userData.collectAsState()

    ProfileScreenContent(
        userData = userData,
        onClearData = {
            usuarioViewModel.limpiarDatosUsuario()
            viewModel.navigateTo(Screen.Landing)
        },
        onNavigateToRegistro = { viewModel.navigateTo(Screen.Registro) },
        onBackClick = { viewModel.navigateBack() },
        onScreenSelected = { screen -> viewModel.navigateTo(screen) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    userData: UserData?,
    onClearData: () -> Unit,
    onNavigateToRegistro: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onScreenSelected: (Screen) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                windowInsets = WindowInsets(0.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
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
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (userData != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Nombre:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = userData.nombre ?: "No registrado",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )

                        HorizontalDivider()

                        Text(
                            text = "Correo electrónico:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = userData.correo ?: "No registrado",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onClearData,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    Text(
                        text = "Cerrar sesión",
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 16.sp
                    )
                }
            } else {
                Text(
                    text = "No hay datos de usuario guardados, por favor regístrese.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onNavigateToRegistro,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Ir a Registro")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MiAppTheme {
        ProfileScreenContent(
            userData = UserData(nombre = "Juan Pérez", correo = "juan@example.com"),
            onClearData = {},
            onBackClick = {},
        ) {
        }
    }
}
