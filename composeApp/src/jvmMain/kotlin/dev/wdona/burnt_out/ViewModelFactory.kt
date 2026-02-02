package dev.wdona.burnt_out

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory

actual class ViewModelFactory {
    actual fun create(): MainViewModel {
        return MainViewModel(DatabaseDriverFactory())
    }
}
