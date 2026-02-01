package dev.wdona.burnt_out

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import burnt_out.composeapp.generated.resources.Res
import burnt_out.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AdaptiveScreen()
        }
    }
}

@Composable
fun AdaptiveScreen() {
    val items = remember { List(20) { "Elemento ${it + 1}" } }
    var selectedItem by remember { mutableStateOf<String?>(null) }

    val onItemSelected = { item: String? ->
        selectedItem = item
    }

    BoxWithConstraints {
        // Decidimos la UI basada en el ancho disponible
        if (maxWidth < 600.dp) {
            // Pantalla pequeña (móvil vertical)
            MobileLayout(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = onItemSelected
            )
        } else {
            // Pantalla grande (tablet, PC)
            DesktopLayout(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Composable
fun MobileLayout(items: List<String>, selectedItem: String?, onItemSelected: (String?) -> Unit) {
    // Si hay un item seleccionado, muestra el detalle, si no, muestra la lista.
    if (selectedItem != null) {
        // Pantalla de detalle en movil
        DetailView(
            item = selectedItem,
            // Añadimos un botón de "atrás" para volver a la lista
            onBack = { onItemSelected(null) }
        )
    } else {
        // Pantalla de lista en movil
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemSelected(item) }
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun DesktopLayout(items: List<String>, selectedItem: String?, onItemSelected: (String?) -> Unit) {
    Row(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f) // Ocupa 1/3 del espacio
        ) {
            items(items) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemSelected(item) }
                        .padding(16.dp),
                    // Resalta el item seleccionado
                    color = if (item == selectedItem) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(2f) // Ocupa 2/3 del espacio
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            if (selectedItem != null) {
                // Muestra el detalle del elemento seleccionado (reutilizar la vista de detalle)
                DetailView(item = selectedItem)
            } else {
                // Mensaje a mostrar si no hay nada seleccionado
                Text("Selecciona un elemento de la lista para ver su detalle")
            }
        }
    }
}

/**
 * Un Composable reutilizable para mostrar la vista de detalle.
 * Se puede usar tanto en Desktop como en Mobile.
 */
@Composable
fun DetailView(item: String, onBack: (() -> Unit)? = null) {
    Card(
        modifier = Modifier.padding(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Si onBack no es nulo (en el caso de movil), mostramos el boton de atras
            onBack?.let {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    IconButton(onClick = it) {
                        Text("Atrás")
                    }
                }
            }

            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "Logo"
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = item,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = "Aquí iría toda la información detallada sobre '$item'.\nPuedes añadir imágenes, más texto, botones, etc.",
                textAlign = TextAlign.Center
            )
        }
    }
}
