package dev.wdona.burnt_out

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.wdona.burnt_out.viewmodels.MainViewModelFactory

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true,
        title = "Burnt-out",
    ) {
        val factory = MainViewModelFactory()
        App(factory)
    }
}