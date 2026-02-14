package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorClient
import dev.wdona.burnt_out.shared.network.Tarea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BurntOutSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val api = KtorClient()

    @Throws(Exception::class)
    suspend fun postTarea(tarea: Tarea): Tarea {
        return api.hacerPeticion(tarea)
    }

    suspend fun dbAddTarea(tarea: Tarea): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                database.appDatabaseQueries.insertTarea(
                    tarea.titulo,
                    tarea.descripcion,
                    tarea.estado,
                    tarea.idTableroPerteneciente.toLong(),
                    tarea.idUsuarioAsignado,
                )
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun crearTarea(tarea: Tarea): Tarea {
        val tareaCreada = postTarea(tarea)
        dbAddTarea(tareaCreada)
        return tareaCreada
    }
}