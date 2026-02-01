package dev.wdona.burnt_out.shared.cache

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqlite.SqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return SqliteDriver(AppDatabase.Schema, "test.db")
    }
}