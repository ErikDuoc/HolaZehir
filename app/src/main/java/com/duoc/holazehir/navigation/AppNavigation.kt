package com.duoc.holazehir.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duoc.holazehir.ui.screens.HomeScreen
import com.duoc.holazehir.ui.screens.LandingScreen
import com.duoc.holazehir.ui.screens.ProfileScreen
import com.duoc.holazehir.ui.screens.RegistroScreen
import com.duoc.holazehir.ui.screens.ResumenScreen
import com.duoc.holazehir.ui.screens.SettingsScreen
import com.duoc.holazehir.ui.theme.MiAppTheme
import com.duoc.holazehir.viewmodel.MainViewModel
import com.duoc.holazehir.viewmodel.UsuarioViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation() {
    MiAppTheme {
        val viewModel: MainViewModel = viewModel()
        val usuarioViewModel: UsuarioViewModel = viewModel()
        val navController = rememberNavController()

        // Escuchar eventos de navegación centralizados de MainViewModel
        LaunchedEffect(key1 = Unit) {
            viewModel.navigationEvents.collectLatest { event ->
                when (event) {
                    is NavigationEvent.NavigateTo -> {
                        navController.navigate(route = event.route.route) {
                            event.popupToRoute?.let {
                                popUpTo(route = it.route) {
                                    inclusive = event.inclusive
                                }
                            }
                            launchSingleTop = event.singleTop
                            restoreState = true
                        }
                    }
                    is NavigationEvent.PopBackStack -> navController.popBackStack()
                    is NavigationEvent.NavigateUp -> navController.navigateUp()
                }
            }
        }

        // Layout base con NavHost
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Landing.route,
                modifier = Modifier.padding(paddingValues = innerPadding)
            ) {
                composable(route = Screen.Landing.route) {
                    LandingScreen(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.Home.route) {
                    HomeScreen(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.Profile.route) {
                    ProfileScreen(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.Settings.route) {
                    SettingsScreen(navController = navController, viewModel = viewModel)
                }
                composable(route = Screen.Registro.route) {
                    RegistroScreen(navController = navController, viewModel = usuarioViewModel)
                }
                composable(route = Screen.Resumen.route) {
                    ResumenScreen(viewModel = usuarioViewModel)
                }
            }
        }
    }
}
