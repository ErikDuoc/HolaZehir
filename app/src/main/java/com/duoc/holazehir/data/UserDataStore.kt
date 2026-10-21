package com.duoc.holazehir.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.userDataStore by preferencesDataStore(name = "preferencias_usuario")

data class UserData(
    val nombre: String?,
    val correo: String?,
)

class UserDataStore(private val context: Context) {

    companion object {
        private val NOMBRE_KEY = stringPreferencesKey("nombre_usuario")
        private val CORREO_KEY = stringPreferencesKey("correo_usuario")
    }

    suspend fun guardarUsuario(nombre: String, correo: String) {
        context.userDataStore.edit { preferences ->
            preferences[NOMBRE_KEY] = nombre
            preferences[CORREO_KEY] = correo
        }
    }

    fun obtenerUsuario(): Flow<UserData?> {
        return context.userDataStore.data.map { preferences ->
            val nombre = preferences[NOMBRE_KEY]
            val correo = preferences[CORREO_KEY]
            if ((nombre != null) || (correo != null)) {
                UserData(nombre = nombre, correo = correo)
            } else {
                null
            }
        }
    }

    suspend fun limpiarUsuario() {
        context.userDataStore.edit { preferences ->
            preferences.remove(NOMBRE_KEY)
            preferences.remove(CORREO_KEY)
        }
    }
}
