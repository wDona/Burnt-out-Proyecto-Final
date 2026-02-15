package dev.wdona.burnt_out.pantallas.equipo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.components.common.InfoTopBarCustom
import dev.wdona.burnt_out.components.tablero.CardTablero
import dev.wdona.burnt_out.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.viewmodels.EquipoViewModel

class LeaderboardScreen(val factory: EquipoViewModelFactory, val idOrg: Long) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        val viewModel = remember { factory.create() }

        LaunchedEffect(idOrg) {
            viewModel.cargarEquiposPorOrganizacion(idOrg)
        }

        LeaderboardContent(viewModel) { navigator.pop() }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardContent(equipoViewModel: EquipoViewModel, onVolver: () -> Unit) {
    val listaEquipos by equipoViewModel.listaEquipos.collectAsState()
    Scaffold(
        topBar = {
            InfoTopBarCustom("Leaderboard", onVolver)
        }
    ) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues).fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 400.dp), // el min size es el tamanio ancho de cada tarjeta
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                items(listaEquipos) { equipo ->
                    CardEquipo(
                        nombreEquipo = equipo.titulo,
                        puntosEquipo = equipo.puntuacion
                    )
                }
            }
        }
    }
}

@Composable
fun CardEquipo(nombreEquipo: String, puntosEquipo: Long?) {
    Column {
        Text("$nombreEquipo tiene ${puntosEquipo ?: "0"} puntos")
    }
}