package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorClient
import dev.wdona.burnt_out.shared.network.Tarea

class BurntOutSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val api = KtorClient()

    @Throws(Exception::class)
    suspend fun postTarea(tarea: Tarea): Tarea {
        return api.hacerPeticion(tarea)
    }

    fun postTareaOffline(tarea: Tarea) {
        database.createTarea(tarea)
    }
}