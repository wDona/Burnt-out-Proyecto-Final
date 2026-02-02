package dev.wdona.burnt_out

import dev.wdona.burnt_out.shared.network.RecepcionTest
import dev.wdona.burnt_out.shared.network.RespuestaTest
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        post("$API_PATH/test") {
            val recibido = call.receive<RespuestaTest>()

            val respuesta = RespuestaTest(
                subject = "Re: ${recibido.subject}. JSON Recibido",
                cuerpo = "Felicidades, el json se ha enviado correctamente"
            )

            call.respond(respuesta)
        }
    }
}