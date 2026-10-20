package com.duoc.holazehir.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

object VibrationUtils {

    // Vibración de acierto: Más firme y notoria (80 ms con intensidad máxima)
    fun vibrarAcierto(context: Context) {
        ejecutarVibracion(context, duracionMs = 80, efectoWaveform = null, esError = false)
    }

    // Vibración de error: Patrón doble más marcado (bzz-bzz potente)
    fun vibrarError(context: Context) {
        // [espera 0ms, vibra 100ms, pausa 60ms, vibra 120ms]
        val patron = longArrayOf(0, 100, 60, 120)
        ejecutarVibracion(context, duracionMs = 0, efectoWaveform = patron, esError = true)
    }

    private fun ejecutarVibracion(
        context: Context,
        duracionMs: Long,
        efectoWaveform: LongArray?,
        esError: Boolean
    ) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        if (!vibrator.hasVibrator()) return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val efecto = if (efectoWaveform != null) {
                // Intensidad máxima (255) para cada segmento del patrón de error
                val amplitudes = intArrayOf(0, 255, 0, 255)
                VibrationEffect.createWaveform(efectoWaveform, amplitudes, -1)
            } else {
                // Intensidad máxima (255) para el acierto
                VibrationEffect.createOneShot(duracionMs, VibrationEffect.DEFAULT_AMPLITUDE)
            }
            vibrator.vibrate(efecto)
        } else {
            @Suppress("DEPRECATION")
            if (efectoWaveform != null) {
                vibrator.vibrate(efectoWaveform, -1)
            } else {
                vibrator.vibrate(duracionMs)
            }
        }
    }
}
