package dev.wdona.burnt_out.pantallas.tablero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.components.common.BotonVolver
import dev.wdona.burnt_out.components.tablero.CardTablero
import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory
import dev.wdona.burnt_out.viewmodels.TableroViewModel

class TablerosScreen(val tableroFactory: TableroViewModelFactory, val tareaViewModelFactory: TareaViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tableroViewModel: TableroViewModel = remember { tableroFactory.create() }
        val idOrg = -1

        LaunchedEffect(idOrg.toLong()) {
            { tableroViewModel.cargarTablerosPorOrganizacion(idOrg.toLong()) }
        }

        MenuTableros(
            onVolver = { navigator.pop() },
                onIrACrearTablero = {
                    navigator.push(MenuCrearTableroScreen(tableroFactory))
                                    },
            tableroViewModel = tableroViewModel,
            onVerTablero = {
                idTablero ->
                    println("Entra a ver tablero")
                    println(idTablero)
                    navigator.push(
                        DetalleTableroScreen(
                            idTablero = idTablero,
                            tareaViewModelFactory = tareaViewModelFactory
                        )
                    )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MenuTableros(tableroViewModel: TableroViewModel, onVolver: () -> Unit, onIrACrearTablero: () -> Unit = {}, onVerTablero: (Long) -> Unit = {}) {
        val listaTableros by tableroViewModel.listaTableros.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tableros") },
                    navigationIcon = {
                        BotonVolver { onVolver() }
                    }
                )
            }
        ) { paddingValues ->
            Column (modifier = Modifier.padding(paddingValues)
            ) {
                Button(
                    onClick = { onIrACrearTablero() }
                ) {
                    Text("Crear nuevo tablero")
                }
                LazyColumn (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                ) {
                    items(listaTableros) {
                            tablero ->
                        CardTablero(tablero.titulo, onClick = {
                            onVerTablero(tablero.idTablero)
                            println("Entra a ver tablero")
                            print("Id tablero: ${tablero.idTablero}")
                        })
                    }
                }
            }
        }
    }
}
