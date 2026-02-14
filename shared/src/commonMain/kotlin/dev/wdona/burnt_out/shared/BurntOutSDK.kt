package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorClient
import dev.wdona.burnt_out.shared.network.Tarea
import dev.wdona.burntout.shared.cache.AppDatabaseQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BurntOutSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val api = KtorClient()

    @Throws(Exception::class)
    private suspend fun _postTarea(tarea: Tarea): Tarea {
        return api.hacerPeticion(tarea)
    }

    private suspend fun _dbAddTarea(tarea: Tarea): Boolean {
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

    private suspend fun _dbAddTareaPendiente(tarea: Tarea): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                database.appDatabaseQueries.insertTareaPendiente(
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

    suspend fun crearTarea(tarea: Tarea): Boolean {
        _dbAddTarea(tarea)
        try {
            _postTarea(tarea)
        } catch (e: Exception) {

            println("Error al enviar al servidor: ${e.message}")
            return false
        }
        return true
    }
}