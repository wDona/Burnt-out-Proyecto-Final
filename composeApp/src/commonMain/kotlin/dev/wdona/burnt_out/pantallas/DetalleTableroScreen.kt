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
import dev.wdona.burnt_out.components.BotonVolver
import dev.wdona.burnt_out.components.CardTarea
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory
import dev.wdona.burnt_out.viewmodels.TareaViewModel

class DetalleTableroScreen(val idTablero: Long, val tareaViewModelFactory: TareaViewModelFactory) : Screen {

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

        DetalleTableroContent(tareaViewModel) { navigator.pop() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleTableroContent(tareaViewModel: TareaViewModel, onVolver: () -> Unit) {
    val listaTareas by tareaViewModel.listaTareas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Titulo del tablero") },
                navigationIcon = {
                    BotonVolver { onVolver() }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)

            ) {
                items(listaTareas) { tarea ->
                    CardTarea(tarea.titulo, tarea.descripcion)
                }
            }
        }
    }
}

