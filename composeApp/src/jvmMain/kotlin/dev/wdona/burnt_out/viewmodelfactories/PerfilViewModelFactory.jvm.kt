package dev.wdona.burnt_out.viewmodelfactories

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.viewmodels.PerfilViewModel

actual class PerfilViewModelFactory {
    actual fun create(): PerfilViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: PerfilViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): PerfilViewModel {
            if (instance == null) {
                instance = PerfilViewModel(driverFactory)
            }
            return instance!!
        }
    }
}