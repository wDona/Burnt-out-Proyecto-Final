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
import dev.wdona.burnt_out.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class MenuInicio(val tareaFactory: TareaViewModelFactory, val equipoFactory: EquipoViewModelFactory, val tableroFactory: TableroViewModelFactory, ) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        HomeContent(
            onNavegarATableros = { navigator.push(TablerosScreen(tareaFactory)) },
            onNavegarAPerfil = { navigator.push(PerfilScreen(tareaFactory)) },
            onNavegarALeaderboard = { navigator.push(LeaderboardScreen(tareaFactory)) },
            onNavegarAEquipo = { navigator.push(EquipoScreen(tareaFactory)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    onNavegarATableros: () -> Unit,
    onNavegarAPerfil: () -> Unit,
    onNavegarALeaderboard: () -> Unit,
    onNavegarAEquipo: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Menú") }) }
    ) { paddingValues: PaddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón para ver tableros
            Button(
                onClick = onNavegarATableros,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
//                    Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Crear Nueva Tarea")
            }

            // Botón para Ver leaderboard
            OutlinedButton(
                onClick = onNavegarALeaderboard,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
//                    Icon(Icons.Default.List, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Mis Tareas")
            }

            // Botón para Ver leaderboard
            OutlinedButton(
                onClick = onNavegarAEquipo,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
//                    Icon(Icons.Default.List, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Mis Tareas")
            }

            // Botón para ver perfil
            TextButton(
                onClick = onNavegarAPerfil,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver mi Perfil")
            }
        }
    }
}