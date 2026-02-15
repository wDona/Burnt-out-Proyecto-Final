package dev.wdona.burnt_out.components.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InfoTopBarCustom(
    title: String,
    onVolver: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            BotonVolver { onVolver() }
        }
    )
}