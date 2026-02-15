package dev.wdona.burnt_out.pantallas.tarea

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
import dev.wdona.burnt_out.viewmodels.TareaViewModel
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class ListaTareasScreen(val tareaFactory: TareaViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val tareaViewModel: TareaViewModel = remember { tareaFactory.create() }

        ListaTareasContent(
            tareaViewModel = tareaViewModel,
            onVolver = { navigator.pop() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaTareasContent(tareaViewModel: TareaViewModel, onVolver: () -> Unit) {
    val listaTareas by tareaViewModel.listaTareas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                navigationIcon = {
                    BotonVolver { onVolver() }
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

            ){
                items(listaTareas) {
                        tarea ->
                    CardTarea(tarea.titulo)
                }
            }
        }
    }
}

