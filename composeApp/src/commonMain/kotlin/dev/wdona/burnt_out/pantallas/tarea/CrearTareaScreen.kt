package dev.wdona.burnt_out.pantallas.tarea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.components.common.AnadirButton
import dev.wdona.burnt_out.components.common.BotonVolver
import dev.wdona.burnt_out.pantallas.SettingsScreen
import dev.wdona.burnt_out.viewmodels.TareaViewModel
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class MenuCrearTareaScreen(val factory: TareaViewModelFactory, val idTablero: Long) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra
        val viewModel: TareaViewModel = remember { factory.create() }

        MenuCrearTareaContent(
            idTablero = idTablero,
            tareaViewModel = viewModel,
            ajustes = {
                navigator.push(SettingsScreen(factory))
            },
            onVolver = { navigator.pop() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCrearTareaContent(idTablero: Long, tareaViewModel: TareaViewModel, ajustes: () -> Unit, onVolver: () -> Unit) {
    // Actualiza la informacion segun uiState
    // val uiState by viewModel.uiState.collectAsState()
    var textStateNombreTarea by remember { mutableStateOf("") }
    var textStateDescripcion by remember { mutableStateOf("") }
    val listaComponentes by tareaViewModel.listaTareas.collectAsState()

    val navigator = LocalNavigator.currentOrThrow // Obtienes el GPS

    Button(onClick = ajustes) {
        Text("Ir a Ajustes")
    }

    val ejecutarEnvio = {
        if (textStateNombreTarea.isNotBlank()) {
            tareaViewModel.crearTarea(0, textStateNombreTarea, textStateDescripcion, idTablero)

            textStateNombreTarea = ""
            textStateDescripcion = ""
            true
        }
        false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                navigationIcon = {
                    BotonVolver { onVolver() }
                },
                actions = {
                    AnadirButton { ejecutarEnvio() }
                }
            )
        },
    ) { paddingValues ->
        Column {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = textStateNombreTarea, // El valor que se muestra
                    onValueChange = { newText ->
                        textStateNombreTarea = newText
                    },
                    label = { Text("Titulo") },
                    placeholder = { Text("Ej. Hacer la compra") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                OutlinedTextField(
                    value = textStateDescripcion, // El valor que se muestra
                    onValueChange = { newText ->
                        textStateDescripcion = newText
                    },
                    label = { Text("Descripcion") },
                    placeholder = { Text("Ej. Comprar patatas y verduras") },
                    modifier = Modifier
                        .fillMaxHeight((1f/6f))
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    singleLine = false
                )
            }
        }
    }
}