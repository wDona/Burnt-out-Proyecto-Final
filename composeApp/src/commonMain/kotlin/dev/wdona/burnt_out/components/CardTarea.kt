package dev.wdona.burnt_out.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dev.wdona.burnt_out.theme.getColorScheme

@Composable
fun CardTarea(tituloTarea: String, descripcionTarea: String) {
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
}