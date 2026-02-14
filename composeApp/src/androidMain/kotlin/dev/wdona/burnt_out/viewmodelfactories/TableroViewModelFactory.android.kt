package dev.wdona.burnt_out.viewmodelfactories

import android.content.Context
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.viewmodels.TableroViewModel

actual class TableroViewModelFactory(private val context: Context) {
    actual fun create(): TableroViewModel {
        return getInstance(DatabaseDriverFactory(context))
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
