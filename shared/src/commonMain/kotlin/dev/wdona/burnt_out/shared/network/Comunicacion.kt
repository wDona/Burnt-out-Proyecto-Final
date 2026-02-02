package dev.wdona.burnt_out.shared.network

import kotlinx.serialization.Serializable

@Serializable
data class RespuestaTest(val subject: String, val cuerpo: String)

@Serializable
data class RecepcionTest(val subject: String, val cuerpo: String)
