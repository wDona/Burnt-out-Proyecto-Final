package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorApi

class BurntOutSDK(databaseDriverFactory: DatabaseDriverFactory) : KtorApi() {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val api = KtorApi()

    fun getDatabase(): AppDatabase = database
    fun getApi(): KtorApi = api
}