# HolaSehir 🇹🇷📱

**HolaSehir** es una aplicación móvil nativa para Android diseñada para la enseñanza interactiva del idioma turco. Desarrollada completamente con **Jetpack Compose**, sigue las mejores prácticas de la industria con arquitectura **MVVM**, gestión de estado reactiva con `StateFlow`, navegación centralizada y persistencia local de preferencias mediante **Jetpack DataStore**.

---

## 🚀 Características Principales

- **Registro de Usuario & Validación de Formulario:** Validación en tiempo real de campos (nombre, correo, contraseña y términos) con persistencia de sesión local.
- **Persistencia Local (DataStore):** Mantiene la sesión del usuario guardada localmente entre reinicios de la app, permitiendo inicio automático en la pantalla principal.
- **Perfil de Usuario & Gestión de Sesión:** Muestra los datos del usuario registrado y permite cerrar sesión o reiniciar el flujo limpiando las preferencias locales.
- **Tutorial Interactivo & Lecciones:** Lecciones interactivas de vocabulario (ej. traducir "Auto" a "araba" en turco).
- **Retroalimentación Háptica (Vibración):** Respuesta táctil para aciertos y errores en ejercicios interactivos mediante `Vibrator` / `VibrationEffect`.
- **Navegación & Animaciones:** Navegación tipo-safe con `AppNavigation`, barra de navegación inferior (`AppNavigationBar`) y transiciones animadas de deslizado entre pantallas.

---

## 🛠️ Tecnologías y Librerías

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Navegación:** [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **Arquitectura:** MVVM (Model-View-ViewModel) + Unidirectional Data Flow (UDF)
- **Concurrencia & Estado:** Kotlin Coroutines & `StateFlow` / `SharedFlow`
- **Persistencia de Datos:** [Jetpack DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore)
- **Efectos Hápticos:** Android `Vibrator` API / `VibrationUtils`

---

## 📁 Estructura del Proyecto

```text
com.duoc.holazehir/
├── data/              # Capa de datos y persistencia (UserDataStore)
├── model/             # Modelos de datos y estados UI (UsuarioUiState, TutorialUiState)
├── navigation/        # Grafo de navegación, eventos y rutas (AppNavigation, Screen, NavigationEvent)
├── ui/
│   ├── components/    # Componentes reutilizables (AppNavigationBar)
│   ├── screens/       # Pantallas composables (LandingScreen, HomeScreen, ProfileScreen, RegistroScreen, TutorialScreen, SettingsScreen)
│   └── theme/         # Sistema de diseño, colores y tipografía de Material3 (Theme.kt)
├── util/              # Utilidades auxiliares (VibrationUtils)
└── viewmodel/         # ViewModels de Jetpack (MainViewModel, UsuarioViewModel, TutorialViewModel)
```

---

## ⚙️ Requisitos de Desarrollo

- **Android Studio:** Jellyfish / Ladybug (o superior)
- **JDK:** 17 o superior
- **Mínima versión de Android (minSdk):** API 24 (Android 7.0)
- **Versión objetivo (targetSdk):** API 34 / 35

---

## 📥 Instalación y Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/HolaZehir.git
   cd HolaZehir
   ```

2. **Abrir en Android Studio:**
   Abre Android Studio y selecciona `Open` eligiendo la carpeta raíz del proyecto.

3. **Sincronizar proyecto con Gradle:**
   Deja que Gradle descargue las dependencias y sincronice el proyecto.

4. **Ejecutar la app:**
   Selecciona un emulador o dispositivo físico con Android 7.0 (API 24) o superior y presiona **Run** (`Shift + F10`).

---

## 📄 Licencia

Este proyecto se distribuye bajo fines educativos para Duoc UC.
