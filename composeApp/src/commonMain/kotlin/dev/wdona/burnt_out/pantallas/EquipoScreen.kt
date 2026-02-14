package dev.wdona.burnt_out.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class EquipoScreen(val factory: EquipoViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        EquipoContent(onVolver = { navigator.pop() })

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EquipoContent(onVolver: () -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Mi equipo") },
                    navigationIcon = {
                        TextButton(onClick = onVolver) {
                            Text("< Volver")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column (modifier = Modifier.padding(paddingValues)
            ) {

            }

        }
    }

}