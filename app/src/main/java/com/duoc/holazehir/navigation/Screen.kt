package com.duoc.holazehir.navigation

// Sealed class para definir rutas tipo-safe en la navegación
sealed class Screen(val route: String) {

    // Rutas simples (sin argumentos): Usamos 'data object'
    data object Home : Screen(route = "home_page")
    data object Profile : Screen(route = "profile_page")
    data object Landing : Screen(route = "landing_page")
    data object Settings : Screen(route = "settings_page")
    data object Registro : Screen(route = "registro")
    data object Resumen : Screen(route = "resumen")

    data object Estado : Screen(route = "estado")

    // Ejemplo de una ruta con argumentos (Opcional)
    data class Detail(val itemId: String) : Screen(route = "detail_page/$itemId") {
        fun buildRoute(): String {
            return route.replace("{itemId}", itemId)
        }
    }
}
