package dev.wdona.burnt_out.pantallas.tablero

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
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
import dev.wdona.burnt_out.components.tarea.CardTarea
import dev.wdona.burnt_out.pantallas.tarea.MenuCrearTareaScreen
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory
import dev.wdona.burnt_out.viewmodels.TareaViewModel

class DetalleTableroScreen(val idTablero: Long, val nombreTablero: String, val tareaViewModelFactory: TareaViewModelFactory) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

//        val perfilViewModel = remember { perfilFactory.create() }
//        val equipoViewModel = remember { equipoFactory.create() }

        val tareaViewModel = remember { tareaViewModelFactory.create() }

        // Cargar tareas cuando se abre la pantalla
        LaunchedEffect(idTablero) {
            tareaViewModel.cargarTareasPorTablero(idTablero)
        }

        DetalleTableroContent(tareaViewModel = tareaViewModel,
            nombreTablero = nombreTablero,
            idTablero = idTablero,
            onVolver = { navigator.pop() },
            onIrACrearTarea = {
                navigator.push(
                    MenuCrearTareaScreen(
                        factory = tareaViewModelFactory,
                        idTablero = idTablero
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DetalleTableroContent(tareaViewModel: TareaViewModel, nombreTablero: String, idTablero: Long ,onVolver: () -> Unit, onIrACrearTarea: (Long) -> Unit) {
    val listaTareas by tareaViewModel.listaTareas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(nombreTablero) },
                navigationIcon = {
                    BotonVolver { onVolver() }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Button(
                onClick = { onIrACrearTarea(idTablero) },
            ) {
                Text("Crear tarea")
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)

            ) {
                items(listaTareas) { tarea ->
                    CardTarea(tarea.titulo)
                }
            }
        }
    }
}

