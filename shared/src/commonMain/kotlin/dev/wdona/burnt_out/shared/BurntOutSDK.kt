package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorApi
import dev.wdona.burnt_out.shared.network.RespuestaTest

class BurntOutSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val api = KtorApi()

    @Throws(Exception::class)
    suspend fun obtenerDatosDePrueba(respuestaTest: RespuestaTest): RespuestaTest {
        return api.enviarDatos(respuestaTest)
    }
}