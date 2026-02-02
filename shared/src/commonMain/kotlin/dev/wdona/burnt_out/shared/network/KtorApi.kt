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


open class KtorApi {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun obtenerRespuesta(): RespuestaTest {
        // Ktor hace el GET y convierte el JSON a tu Data Class automáticamente
        return client.get("http://$HOST:8080/api/test").body()
    }

    suspend fun enviarDatos(respuestaTest: RespuestaTest): RespuestaTest {
        val test = RespuestaTest(subject = "Esto es una prueba", cuerpo = "Esto es el cuerpo de la prueba")

        val respuesta: RespuestaTest = client.post("http://$HOST:8080/api/test") {
            contentType(ContentType.Application.Json)
            setBody<RespuestaTest>(test) // Enviamos el objeto (Ktor lo hace JSON)
        }.body() // Recibimos la respuesta (Ktor la hace objeto)

        return respuesta
    }
}