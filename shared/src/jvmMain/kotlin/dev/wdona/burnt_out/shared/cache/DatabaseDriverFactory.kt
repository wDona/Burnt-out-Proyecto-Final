package dev.wdona.burnt_out.shared.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = "test.db"
        val dbFile = File(databasePath)
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:$databasePath")

        val isNewDatabase = !dbFile.exists()
        if (isNewDatabase) {
            AppDatabase.Schema.create(driver)
            val database = AppDatabase(driver)

            insertarDatosIniciales(database)
        }

        return driver
    }

    private fun insertarDatosIniciales(database: AppDatabase) {
        database.appDatabaseQueries.insertOrgbase()
        database.appDatabaseQueries.insertAjustes()
        database.appDatabaseQueries.insertEquipoBase()
    }
}