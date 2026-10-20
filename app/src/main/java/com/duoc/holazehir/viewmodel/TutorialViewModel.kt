package com.duoc.holazehir.viewmodel

import androidx.lifecycle.ViewModel
import com.duoc.holazehir.model.TutorialUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TutorialViewModel : ViewModel() {

    private val _estado = MutableStateFlow(TutorialUiState())
    val estado: StateFlow<TutorialUiState> = _estado

    fun onPalabraChange(valor: String) {
        _estado.update {
            it.copy(
                palabra = valor,
                mensajeFeedback = null,
                esCorrecto = null
            )
        }
    }

    fun comprobar() {
        val estadoActual = _estado.value
        val palabraIngresada = estadoActual.palabra.trim()

        if (palabraIngresada.equals("araba", ignoreCase = true)) {
            _estado.update {
                it.copy(
                    mensajeFeedback = "¡Muy bien, sigue así!",
                    esCorrecto = true
                )
            }
        } else {
            _estado.update {
                it.copy(
                    mensajeFeedback = "Sigue intentando, ¡tú puedes!",
                    esCorrecto = false
                )
            }
        }
    }
}
