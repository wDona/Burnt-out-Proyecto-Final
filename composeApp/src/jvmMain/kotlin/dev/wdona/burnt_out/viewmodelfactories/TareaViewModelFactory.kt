package dev.wdona.burnt_out.viewmodelfactories

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.viewmodels.TareaViewModel

actual class TareaViewModelFactory {
    actual fun create(): TareaViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: TareaViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): TareaViewModel {
            if (instance == null) {
                instance = TareaViewModel(driverFactory)
            }
            return instance!!
        }
    }
}