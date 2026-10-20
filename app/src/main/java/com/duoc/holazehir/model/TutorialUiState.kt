package com.duoc.holazehir.model

// Modelo principal que representa el estado del formulario del usuario
data class TutorialUiState(
    val palabra: String = "",
    val errores: UsuarioErrores = UsuarioErrores()
)

// Modelo que almacena posibles errores individuales del formulario
data class TutorialErrores(
    val nombre: String? = null,
)