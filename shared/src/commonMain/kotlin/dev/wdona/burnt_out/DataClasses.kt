package dev.wdona.burnt_out

import kotlinx.serialization.Serializable

@Serializable
data class Tarea(val idTarea: Long, val titulo: String, val descripcion: String?, val estado: String, val idTableroPerteneciente: Long, val idUsuarioAsignado: Long, val idSubtareas: List<Int>?)

@Serializable
data class Tablero(val idTablero: Long, val titulo: String, val idOrganizacion: Long, val idEquipo: Long?) // Id equipo anadido para saber pertenencia

@Serializable
data class Equipo(val idEquipo: Long, val titulo: String, val puntuacion: Long?, val idOrganizacion: Long, val idMiembros: List<Long>) // Puntuacion anadida

@Serializable
data class Organizacion(val idOrganizacion: Long, val nombre: String)

@Serializable
data class Usuario(val idUsuario: Long, val username: String, val password: String, val nombre: String, val riesgoBurnout: Double?, val descripcion: String?, val idOrganizacion: Long, val idEquipo: Long) // ID equipo anadido para saber a que equipo pertenece

@Serializable
data class Subtarea(val idSubtarea: Long, val titulo: String, val descripcion: String?, val completado: Boolean, val idTareaPerteneciente: Long)

@Serializable
data class Respuesta(val idUsuario: Long, val idPregunta: Long, val anonimo: Boolean, val respuesta: String)

@Serializable
data class Pregunta(val idPregunta: Long, val pregunta: String, val idOrganizacion: Long)

