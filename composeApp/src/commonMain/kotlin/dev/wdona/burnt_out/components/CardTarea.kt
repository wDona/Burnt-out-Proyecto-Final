package dev.wdona.burnt_out.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.theme.getColorScheme

@Composable
fun CardTarea(tituloTarea: String, descripcionTarea: String?) {
    Button (
        modifier = Modifier
            .fillMaxWidth(1f/3f)
            .padding(16.dp),
        onClick = {

        }
    ) {
        Text(text = tituloTarea,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (descripcionTarea != null && descripcionTarea.isNotBlank()) {
            Text(text = descripcionTarea,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
        }
    }
}