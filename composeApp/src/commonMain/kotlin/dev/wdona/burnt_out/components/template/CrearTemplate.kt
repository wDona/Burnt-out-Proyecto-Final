package dev.wdona.burnt_out.components.template

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import dev.wdona.burnt_out.components.common.CreationTopBarCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearTemplate(titulo: String, onVolver: () -> Unit, onCrear: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = {
            CreationTopBarCustom(titulo, onVolver, onCrear)
        },
    ) { paddingValues ->
        content(paddingValues)
    }
}

