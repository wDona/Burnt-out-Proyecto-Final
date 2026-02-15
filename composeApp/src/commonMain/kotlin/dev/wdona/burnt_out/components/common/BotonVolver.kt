package dev.wdona.burnt_out.components.common

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun BotonVolver(onVolver: () -> Unit) {
    TextButton(onClick = onVolver) {
        Text("<-")
    }
}