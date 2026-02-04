package dev.wdona.burnt_out.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.viewmodels.MainViewModelFactory

class MenuInicio(val factory: MainViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        HomeContent(
            onNavegarACrear = { navigator.push(MenuCrearTareaScreen(factory)) },
            onNavegarAPerfil = { navigator.push(PerfilScreen(factory)) },
            onNavegarATareas = { navigator.push(ListaTareasScreen(factory)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    onNavegarACrear: () -> Unit,
    onNavegarAPerfil: () -> Unit,
    onNavegarATareas: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Burn't out - Menú") }) }
    ) { paddingValues: PaddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para Crear Tarea
            Button(
                onClick = onNavegarACrear,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
//                    Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Crear Nueva Tarea")
            }

            // Botón para Ver Tareas
            OutlinedButton(
                onClick = onNavegarATareas,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
//                    Icon(Icons.Default.List, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Mis Tareas")
            }

            // Botón para Perfil
            TextButton(
                onClick = onNavegarAPerfil,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver mi Perfil")
            }
        }
    }
}