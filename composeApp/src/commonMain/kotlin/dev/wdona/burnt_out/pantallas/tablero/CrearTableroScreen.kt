package dev.wdona.burnt_out.pantallas.tablero

import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodels.TableroViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import dev.wdona.burnt_out.components.common.BotonVolver


class MenuCrearTableroScreen(val factory: TableroViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra
        val viewModel: TableroViewModel = remember { factory.create() }

        MenuCrearTableroContent(
            tableroViewModel = viewModel,
            onVolver = { navigator.pop() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuCrearTableroContent(tableroViewModel: TableroViewModel, onVolver: () -> Unit) {
    // Actualiza la informacion segun uiState
    // val uiState by viewModel.uiState.collectAsState()
    var textStateNombreTablero by remember { mutableStateOf("") }
    val listaComponentes by tableroViewModel.listaTableros.collectAsState()
    val focusRequesterNombreTablero = remember { FocusRequester() }

    val navigator = LocalNavigator.currentOrThrow

    LaunchedEffect(Unit) {
        focusRequesterNombreTablero.requestFocus()
    }

    val ejecutarEnvio: () -> Unit = {
        if (textStateNombreTablero.isNotBlank()) {
            tableroViewModel.crearTablero(0, textStateNombreTablero)

            textStateNombreTablero = ""
            focusRequesterNombreTablero.requestFocus()
            true
        }
        false
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear tablero") },
                navigationIcon = {
                    BotonVolver { onVolver() }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = ejecutarEnvio,
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
                    value = textStateNombreTablero, // El valor que se muestra
                    onValueChange = { newText ->
                        textStateNombreTablero = newText
                    },
                    label = { Text("Titulo") },
                    placeholder = { Text("Ej. Lista de la compra") },
                    singleLine = true,
                    modifier = Modifier
                        .focusRequester(focusRequesterNombreTablero)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
            }
        }
    }
}