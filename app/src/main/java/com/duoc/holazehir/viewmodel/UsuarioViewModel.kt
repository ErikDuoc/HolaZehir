package com.duoc.holazehir.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.duoc.holazehir.data.UserData
import com.duoc.holazehir.data.UserDataStore
import com.duoc.holazehir.model.UsuarioErrores
import com.duoc.holazehir.model.UsuarioUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val userDataStore = UserDataStore(application)

    // Estado interno mutable
    private val _estado = MutableStateFlow(UsuarioUiState())

    // Estado expuesto para la UI
    val estado: StateFlow<UsuarioUiState> = _estado

    private val _isLoading = MutableStateFlow(value = true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _userData = MutableStateFlow<UserData?>(null)
    val userData: StateFlow<UserData?> = _userData

    init {
        cargarUsuarioGuardado()
    }

    private fun cargarUsuarioGuardado() {
        viewModelScope.launch {
            delay(1500L) // Simular consulta a base de datos
            userDataStore.obtenerUsuario().collect { data ->
                _userData.value = data
                _isLoading.value = false
            }
        }
    }

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

    // Actualiza checkbox de aceptacion
    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    // Validacion global del formulario
    fun validarFormulario(): Boolean {
        val (nombre, correo, clave, aceptaTerminos) = estado.value
        val errores = UsuarioErrores(
            nombre = if (nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!correo.contains("@")) "Correo inválido" else null,
            clave = if (clave.length < 5) "Debe tener al menos 5 caracteres" else null,
        )

        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }
        val esValido = !hayErrores && aceptaTerminos
        if (esValido) {
            viewModelScope.launch {
                userDataStore.guardarUsuario(nombre, correo)
            }
        }
        return esValido
    }

    fun limpiarDatosUsuario() {
        viewModelScope.launch {
            userDataStore.limpiarUsuario()
            _userData.value = null
            _estado.update {
                UsuarioUiState()
            }
        }
    }
}
