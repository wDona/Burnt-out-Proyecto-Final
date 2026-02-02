package dev.wdona.burnt_out

import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory

expect class ViewModelFactory {
    fun create(): MainViewModel
}
