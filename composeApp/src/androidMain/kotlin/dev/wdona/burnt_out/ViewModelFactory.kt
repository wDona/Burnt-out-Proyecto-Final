package dev.wdona.burnt_out

import android.content.Context
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory

actual class ViewModelFactory(private val context: Context) {
    actual fun create(): MainViewModel {
        return MainViewModel(DatabaseDriverFactory(context))
    }
}
