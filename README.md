# Burn't out

[cite_start]**Burn't out** es una plataforma de gestión de tareas y proyectos diseñada para 
equilibrar la productividad con el bienestar mental. 
[cite_start]Inspirada en herramientas como Trello, su objetivo principal es prevenir el síndrome de 
Burnout en entornos corporativos mediante el monitoreo saludable del estado de ánimo y la carga de 
trabajo[cite: 3, 4].

---

## 🌟 Características Principales

* [cite_start]**Gestión Visual:** Organización mediante tableros, tarjetas, tareas y subtareas[cite: 3, 9].
* [cite_start]**Monitoreo de Bienestar:** Encuestas rápidas (menos de 15 segundos) y anónimas sobre
* estrés y ánimo[cite: 4, 40].
* [cite_start]**Prevención Activa:** La app evalúa el riesgo de agotamiento, genera alertas y puede 
* limitar la asignación de tareas si el riesgo es alto[cite: 5, 87].
* [cite_start]**Cooperación y Gamificación:** Sistema de recompensas y tablas de clasificación para 
* fomentar el trabajo en equipo y las pausas necesarias[cite: 6, 18, 24].
* [cite_start]**Modo Offline:** Capacidad de trabajar sin conexión, priorizando la sincronización de 
* cambios al recuperar el acceso a internet[cite: 27].

---

## 🛠️ Stack Tecnológico

[cite_start]El proyecto utiliza **Kotlin Multiplatform (KMP)** para compartir lógica entre plataformas 
y **Compose** para la interfaz de usuario[cite: 39, 247].

* [cite_start]**Cliente:** Android y Desktop[cite: 43].
* [cite_start]**Servidor:** API construida con Ktor[cite: 248].
* [cite_start]**Base de Datos:** * **Local:** SQLite con SQLDelight[cite: 249, 258].
  * [cite_start]**Nube:** MariaDB / PostgreSQL[cite: 250, 258].
* [cite_start]**Seguridad:** Cifrado TLS, hashing de contraseñas y autenticación mediante 
* JWT[cite: 32, 33, 259].

---

## 🚀 Guía de Build y Ejecución

### Requisitos Previos
* JDK 11 o superior.
* Android Studio o IntelliJ IDEA (con el plugin KMP).

### 🖥️ Escritorio (Desktop JVM)
Para compilar y ejecutar la versión de escritorio:
- **macOS/Linux:** `./gradlew :composeApp:run`
- **Windows:** `.\gradlew.bat :composeApp:run`

### 📱 Android
Para instalar la aplicación en un dispositivo o emulador:
- **macOS/Linux:** `./gradlew :composeApp:installDebug`
- **Windows:** `.\gradlew.bat :composeApp:installDebug`

### 🌐 Servidor
Para iniciar la API del servidor:
- **macOS/Linux:** `./gradlew :server:run`
- **Windows:** `.\gradlew.bat :server:run`
- 
---

## 🛡️ Privacidad y Seguridad
* [cite_start]**Anonimato:** Configurable por el usuario para las encuestas de bienestar[cite: 22].
* [cite_start]**Protección de Datos:** Los logs no contienen información personal sensible[cite: 35, 264].
* [cite_start]**Consentimiento:** Se requiere aceptación explícita antes del tratamiento de datos[cite: 36, 264].

---
[cite_start]*Desarrollado por wDona*[cite: 252].