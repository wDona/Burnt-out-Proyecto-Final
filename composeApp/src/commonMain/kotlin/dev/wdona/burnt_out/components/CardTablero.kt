package dev.wdona.burnt_out.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CardTablero(tituloTablero: String) {
    Button (
        modifier = Modifier
            .fillMaxWidth(1f/3f)
            .padding(16.dp),
        onClick = {

        }
    ) {
        Text(text = tituloTablero,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}