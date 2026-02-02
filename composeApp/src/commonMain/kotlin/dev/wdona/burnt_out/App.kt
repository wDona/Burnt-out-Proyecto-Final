package dev.wdona.burnt_out

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun App(factory: ViewModelFactory) {
    val viewModel: MainViewModel = remember { factory.create() }
    val uiState by viewModel.uiState.collectAsState()

    // Use LaunchedEffect to fetch data only once
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Check the state and update the UI accordingly
            val data = uiState
            if (data == null) {
                Text("Cargando datos desde la API...")
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("¡Datos recibidos!", style = MaterialTheme.typography.headlineSmall)
                    Text("Subject: ${data.subject}")
                    Text("Cuerpo: ${data.cuerpo}")
                }
            }
        }
    }
}
