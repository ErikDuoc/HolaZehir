package com.duoc.holazehir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.duoc.holazehir.ui.theme.HomeScreen
import com.duoc.holazehir.ui.theme.MiAppTheme // Reemplaza por el nombre del tema de tu proyecto

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aplicamos el tema general del proyecto
            MiAppTheme {
                HomeScreen()
            }
        }
    }
}