package dev.wdona.burnt_out.viewmodelfactories

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.viewmodels.TableroViewModel

actual class TableroViewModelFactory {
    actual fun create(): TableroViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: TableroViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): TableroViewModel {
            if (instance == null) {
                instance = TableroViewModel(driverFactory)
            }
            return instance!!
        }
    }
}