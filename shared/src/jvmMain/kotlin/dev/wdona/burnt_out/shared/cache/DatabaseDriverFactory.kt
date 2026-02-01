package dev.wdona.burnt_out.shared.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val databasePath = "test.db"
        val dbFile = File(databasePath)
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:$databasePath")

        if (!dbFile.exists()) {
            AppDatabase.Schema.create(driver)
        }

        return driver
    }
}