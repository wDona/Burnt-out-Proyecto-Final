package dev.wdona.burnt_out

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.wdona.burnt_out.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.PerfilViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        alwaysOnTop = true,
        title = "Burnt-out",
    ) {
        val tareaViewModelFactory = TareaViewModelFactory()
        val equipoViewModelFactory = EquipoViewModelFactory()
        val perfilViewModelFactory = PerfilViewModelFactory()
        val tableroViewModelFactory = TableroViewModelFactory()
        App(
            tareaViewModelFactory,
            equipoViewModelFactory = equipoViewModelFactory,
            perfilViewModelFactory = perfilViewModelFactory,
            tableroViewModelFactory = tableroViewModelFactory
        )
    }
}