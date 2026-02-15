package dev.wdona.burnt_out.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


open class KtorClient {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun obtenerRespuesta(): Tarea {
        // Ktor hace el GET y convierte el JSON a tu Data Class automáticamente
        return client.get("http://$HOST:8080/api/test").body()
    }

    suspend fun enviar(tarea: Tarea): Tarea {
        val respuesta: Tarea = client.post("http://$HOST:8080/api/test") {
            contentType(ContentType.Application.Json)
            setBody<Tarea>(tarea) // Enviamos el objeto (Ktor lo hace JSON)
        }.body() // Recibimos la respuesta (Ktor la hace objeto)

        return respuesta
    }

    suspend fun enviar(tablero: Tablero): Tablero {
        val respuesta: Tablero = client.post("http://$HOST:8080/api/test") {
            contentType(ContentType.Application.Json)
            setBody<Tablero>(tablero) // Enviamos el objeto (Ktor lo hace JSON)
        }.body() // Recibimos la respuesta (Ktor la hace objeto)

        return respuesta
    }
}