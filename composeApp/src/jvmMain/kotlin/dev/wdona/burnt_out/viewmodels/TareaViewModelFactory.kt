package dev.wdona.burnt_out.viewmodels

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory

actual class MainViewModelFactory {
    actual fun create(): MainViewModel {
        return getInstance(DatabaseDriverFactory())
    }

    companion object {
        private var instance: MainViewModel? = null
        fun getInstance(driverFactory: DatabaseDriverFactory): MainViewModel {
            if (instance == null) {
                instance = MainViewModel(driverFactory)
            }
            return instance!!
        }
    }
}