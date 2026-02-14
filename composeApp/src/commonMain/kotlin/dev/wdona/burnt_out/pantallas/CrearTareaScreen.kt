package dev.wdona.burnt_out.pantallas

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
import dev.wdona.burnt_out.viewmodels.TareaViewModel
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class MenuCrearTareaScreen(val factory: TareaViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra
        val viewModel: TareaViewModel = remember { factory.create() }

        MenuCrearTareaContent(
            viewModel = viewModel,
            ajustes = {
                navigator.push(SettingsScreen(factory))
            },
            onVolver = { navigator.pop() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCrearTareaContent(viewModel: TareaViewModel, ajustes: () -> Unit, onVolver: () -> Unit) {
    // Actualiza la informacion segun uiState
    // val uiState by viewModel.uiState.collectAsState()
    var textStateSubject by remember { mutableStateOf("") }
    var textStateCuerpo by remember { mutableStateOf("") }
    val listaComponentes by viewModel.listaTareas.collectAsState()
    val focusRequesterCuerpo = remember { FocusRequester() }
    val focusRequesterSubject = remember { FocusRequester() }

    val navigator = LocalNavigator.currentOrThrow // Obtienes el GPS

    Button(onClick = ajustes) {
        Text("Ir a Ajustes")
    }

    LaunchedEffect(Unit) {
        focusRequesterSubject.requestFocus()
    }

    val ejecutarEnvio = {
        if (textStateSubject.isNotBlank()) {
            viewModel.crearTarea(textStateSubject, textStateCuerpo)

            textStateSubject = ""
            textStateCuerpo = ""
            focusRequesterSubject.requestFocus()
            true
        }
        false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Tareas") },
                navigationIcon = {
                    TextButton(onClick = onVolver) {
                        Text("< Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (ejecutarEnvio()) {
                        focusRequesterCuerpo.requestFocus()
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("+")
            }
        },
        contentWindowInsets = WindowInsets.ime,
    ) { paddingValues ->
        Column {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = textStateSubject, // El valor que se muestra
                    onValueChange = { newText ->
                        textStateSubject = newText
                    },
                    label = { Text("Titulo") },
                    placeholder = { Text("Ej. Hacer la compra") },
                    singleLine = true,
                    modifier = Modifier
                        .focusRequester(focusRequesterSubject)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                OutlinedTextField(
                    value = textStateCuerpo, // El valor que se muestra
                    onValueChange = { newText ->
                        textStateCuerpo = newText
                    },
                    label = { Text("Descripcion") },
                    placeholder = { Text("Ej. Comprar patatas y verduras") },
                    modifier = Modifier
                        .focusRequester(focusRequesterCuerpo)
                        .fillMaxHeight((1f/6f))
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    singleLine = false
                )
            }
        }
    }
}