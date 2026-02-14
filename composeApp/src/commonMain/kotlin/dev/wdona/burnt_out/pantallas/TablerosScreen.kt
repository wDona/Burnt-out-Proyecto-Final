package dev.wdona.burnt_out.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.components.CardTablero
import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodels.TableroViewModel

class TablerosScreen(val factory: TableroViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tableroViewModel: TableroViewModel = remember { factory.create() }

        MenuTableros(
            ajustes = {}, onVolver = {},
            tableroViewModel = tableroViewModel
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MenuTableros(tableroViewModel: TableroViewModel, ajustes: () -> Unit, onVolver: () -> Unit) {
        val listaTableros by tableroViewModel.listaTableros.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tableros") },
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
                LazyColumn (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                ) {
                    items(listaTableros) {
                            tablero ->
                        CardTablero(tablero.titulo)
                    }
                }
            }
        }
    }
}
