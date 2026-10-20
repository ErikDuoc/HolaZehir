package com.duoc.holazehir.navigation

// Sealed class para definir rutas tipo-safe en la navegación
sealed class Screen(val route: String, val name: String? = null) {

    // Rutas simples (sin argumentos): Usamos 'data object'
    data object Home : Screen(route = "home_page", name = "Inicio")
    data object Profile : Screen(route = "profile_page", name = "Perfil")
    data object Landing : Screen(route = "landing_page", name = "Landing")
    data object Settings : Screen(route = "settings_page", name = "Configuración")
    data object Registro : Screen(route = "registro", name = "Registro")
    data object Resumen : Screen(route = "resumen", name = "Resumen registro")

    data object Tutorial : Screen(route = "tutorial_page", name = "Tutorial")

    // Ejemplo de una ruta con argumentos (Opcional)
    data class Detail(val itemId: String) : Screen(route = "detail_page/$itemId") {
        fun buildRoute(): String {
            return route.replace("{itemId}", itemId)
        }
    }
}
