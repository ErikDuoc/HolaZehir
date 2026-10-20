package com.duoc.holazehir.model

// Modelo principal que representa el estado del tutorial
data class TutorialUiState(
    val palabra: String = "",
    val mensajeFeedback: String? = null,
    val esCorrecto: Boolean? = null
)
