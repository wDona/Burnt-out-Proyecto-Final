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
import dev.wdona.burnt_out.viewmodelfactories.PerfilViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class MenuInicio(val equipoFactory: EquipoViewModelFactory, val tableroFactory: TableroViewModelFactory, val perfilFactory: PerfilViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        HomeContent(
            onNavegarATableros = { navigator.push(TablerosScreen(tableroFactory)) },
            onNavegarAPerfil = { navigator.push(PerfilScreen(perfilFactory)) },
            onNavegarALeaderboard = { navigator.push(LeaderboardScreen(equipoFactory)) },
            onNavegarAEquipo = { navigator.push(EquipoScreen(equipoFactory)) }
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
                Spacer(Modifier.width(8.dp))
                Text("Tableros")
            }

            // Botón para Ver leaderboard
            OutlinedButton(
                onClick = onNavegarALeaderboard,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
                Spacer(Modifier.width(8.dp))
                Text("Leaderboard")
            }

            // Botón para Ver equipo en el que esta el usuario
            OutlinedButton(
                onClick = onNavegarAEquipo,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
                Spacer(Modifier.width(8.dp))
                Text("Mi Equipo")
            }

            // Botón para ver perfil
            TextButton(
                onClick = onNavegarAPerfil,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
                Text("Ver mi Perfil")
            }
        }
    }
}