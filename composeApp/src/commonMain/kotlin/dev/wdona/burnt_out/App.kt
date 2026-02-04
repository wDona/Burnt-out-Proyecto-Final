package dev.wdona.burnt_out

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(factory: ViewModelFactory) {
    CustomMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isSystemInDarkTheme()) DarkColorScheme.background else LightColorScheme.background

        ) {
            PantallaCrearTarea(factory)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearTarea(factory: ViewModelFactory) {
    val viewModel: MainViewModel = remember { factory.create() }
    // Actualiza la informacion segun uiState
    // val uiState by viewModel.uiState.collectAsState()
    var textStateSubject by remember { mutableStateOf("") }
    var textStateCuerpo by remember { mutableStateOf("") }
    val listaComponentes by viewModel.listaComponentes.collectAsState()
    val focusRequesterCuerpo = remember { FocusRequester() }
    val focusRequesterSubject = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequesterSubject.requestFocus()
    }

    @Composable
    fun crearComponente(tituloTarea: String, descripcionTarea: String, viewModel: MainViewModel) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(1f/3f)
                .background(getColorScheme().secondaryContainer)
        ) {

            Text(text = tituloTarea,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())

            if (descripcionTarea.isNotBlank()) {
                Text(text = descripcionTarea,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            }
        }

        viewModel.limpiarUiState()
    }

    val ejecutarEnvio = {
        if (textStateSubject.isNotBlank()) {
            viewModel.enviarTarea(textStateSubject, textStateCuerpo)

            textStateSubject = ""
            textStateCuerpo = ""
            focusRequesterSubject.requestFocus()
            true
        }
        false
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text("Burn't out") }) },
        floatingActionButton = { FloatingActionButton(
            onClick = {
                ejecutarEnvio()
            }) {
            Text("+", style = MaterialTheme.typography.titleLarge)
        }
        }
    ) { paddingValues ->
        Column {
            Column(modifier = Modifier.padding(paddingValues)) {
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
                        .fillMaxHeight((1f/2f))
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    singleLine = false
                )
            }
            LazyColumn (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)

            ){
                items(listaComponentes) {
                        componente ->
                    crearComponente(componente.subject, componente.cuerpo, viewModel)
                }
            }
        }
    }
}


