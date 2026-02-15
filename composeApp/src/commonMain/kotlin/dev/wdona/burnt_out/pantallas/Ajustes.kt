package dev.wdona.burnt_out.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.components.common.BotonVolver
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

// Pantalla de Ajustes
class SettingsScreen(val factory: TareaViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        Column {
            Text("Configuración")
            BotonVolver { navigator.pop() }
        }
    }
}