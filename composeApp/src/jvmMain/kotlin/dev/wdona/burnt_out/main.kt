package dev.wdona.burnt_out

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Burnt-out",
    ) {
        val factory = ViewModelFactory()
        App(factory)
    }
}