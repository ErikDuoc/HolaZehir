package com.duoc.holazehir.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.duoc.holazehir.navigation.Screen

@Composable
fun AppNavigationBar(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier,
    items: List<Screen> = listOf(Screen.Home, Screen.Landing, Screen.Profile)
) {
    NavigationBar(modifier = modifier) {
        items.forEach { screen ->
            val selected = currentScreen == screen
            NavigationBarItem(
                selected = selected,
                onClick = { onScreenSelected(screen) },
                label = { Text(text = screen.route) },
                icon = {
                    val icon = when (screen) {
                        Screen.Home -> Icons.Default.Home
                        Screen.Profile -> Icons.Default.Person
                        Screen.Settings -> Icons.Default.Settings
                        Screen.Landing -> Icons.Default.Home
                        else -> Icons.Default.Home
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = screen.route
                    )
                }
            )
        }
    }
}
