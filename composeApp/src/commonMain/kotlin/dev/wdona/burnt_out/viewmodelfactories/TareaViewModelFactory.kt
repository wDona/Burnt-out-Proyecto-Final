package dev.wdona.burnt_out.viewmodelfactories

import dev.wdona.burnt_out.viewmodels.TareaViewModel

expect class TareaViewModelFactory {
    fun create(): TareaViewModel
}