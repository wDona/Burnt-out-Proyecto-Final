package dev.wdona.burnt_out.shared.cache

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import java.io.File

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        val databasePath = "burnt_out.db"
        val dbFile = File(databasePath)
        val driver: SqlDriver = AndroidSqliteDriver(AppDatabase.Schema, context, databasePath)

        val database = AppDatabase(driver)
        if (database.appDatabaseQueries.getOrganizacionById(1).executeAsOneOrNull() == null) {
            insertarDatosIniciales(database)
        }

        return driver
    }

    private fun insertarDatosIniciales(database: AppDatabase) {
        database.appDatabaseQueries.insertOrgbase()
        database.appDatabaseQueries.insertAjustes()
        database.appDatabaseQueries.insertEquipoBase()
        database.appDatabaseQueries.insertUsuarioBase()
    }
}