package dev.wdona.burnt_out.viewmodels

import android.content.Context
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory

actual class MainViewModelFactory(private val context: Context) {

    actual fun create(): MainViewModel {
        return getInstance(DatabaseDriverFactory(context))
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