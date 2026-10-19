package com.duoc.holazehir.viewmodel

import androidx.lifecycle.ViewModel
import com.duoc.holazehir.model.UsuarioErrores
import com.duoc.holazehir.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    // Estado interno mutable
    private val _estado = MutableStateFlow(UsuarioUiState())

    // Estado expuesto para la UI
    val estado: StateFlow<UsuarioUiState> = _estado

    // Actualiza el campo nombre y limpia su error
    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    // Actualiza el campo correo y limpia su error
    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    // Actualiza el campo clave y limpia su error
    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    // Actualiza el campo direccion y limpia su error
    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    // Actualiza checkbox de aceptacion
    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    // Validacion global del formulario
    fun validarFormulario(): Boolean {
        val estadoActual = estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo inválido" else null,
            clave = if (estadoActual.clave.length < 5) "Debe tener al menos 5 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }
        return !hayErrores && estadoActual.aceptaTerminos
    }
}