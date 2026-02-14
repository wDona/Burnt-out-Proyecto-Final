package dev.wdona.burnt_out.shared.network

import kotlinx.serialization.Serializable

@Serializable
data class Tarea(val idTarea: Int, val titulo: String, val descripcion: String?, val estado: String?, val idTableroPerteneciente: Int, val idUsuarioAsignado: String?, val idSubtareas: List<Int>?)

@Serializable
data class Tablero(val idTablero: String, val titulo: String, val idOrganizacion: Int, val idEquipo: Int) // Id equipo anadido para saber pertenencia

@Serializable
data class Equipo(val idEquipo: Int, val titulo: String, val puntuacion: Int, val idOrganizacion: Int, val idMiembros: List<Int>) // Puntuacion anadida

@Serializable
data class Organizacion(val idOrganizacion: Int, val nombre: String)

@Serializable
data class Usuario(val idUsuario: Int, val username: String, val password: String, val nombre: String, val riesgoBurnout: Float, val descripcion: String?, val idOrganizacion: Int, val idEquipo: Int) // ID equipo anadido para saber a que equipo pertenece

@Serializable
data class Subtarea(val idSubtarea: Int, val titulo: String, val descripcion: String?, val completado: Boolean, val idTareaPerteneciente: Int)

@Serializable
data class Respuesta(val idUsuario: Int, val idPregunta: Int, val anonimo: Boolean, val respuesta: String)

@Serializable
data class Pregunta(val idPregunta: Int, val pregunta: String, val idOrganizacion: Int)

