package dev.wdona.burnt_out

import kotlinx.serialization.Serializable

@Serializable
data class Tarea(val titulo: String, val descripcion: String)

@Serializable
data class RecepcionTest(val subject: String, val cuerpo: String)

