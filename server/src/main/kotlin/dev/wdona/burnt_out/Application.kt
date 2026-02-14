package dev.wdona.burnt_out

import dev.wdona.burnt_out.shared.network.Tarea
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
            val recibido = call.receive<Tarea>()

            val respuesta = Tarea(
                idTarea = System.currentTimeMillis().toInt(),
                titulo = recibido.titulo,
                descripcion = recibido.descripcion,
                estado = "pendiente",
                idTableroPerteneciente = recibido.idTableroPerteneciente,
                idUsuarioAsignado = recibido.idUsuarioAsignado,
                idSubtareas = recibido.idSubtareas
            )

            call.respond(respuesta)
        }
    }
}