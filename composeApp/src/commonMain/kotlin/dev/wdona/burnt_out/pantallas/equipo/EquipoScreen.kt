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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.wdona.burnt_out.components.common.BotonVolver
import dev.wdona.burnt_out.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.viewmodels.EquipoViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import dev.wdona.burnt_out.Usuario

class EquipoScreen(val factory: EquipoViewModelFactory) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow // Para poder volver o ir a otra

        val viewModel = remember { factory.create() }
        val idEquipo = 1L // TODO: COGER ID DEL EQUIPO DEL USUARIO ACTIVO

        LaunchedEffect(idEquipo) {
            viewModel.cargarEquipoPorId(idEquipo)
            viewModel.cargarMiembrosEquipo(idEquipo)
        }

        EquipoContent(viewModel, onVolver = { navigator.pop() })

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun EquipoContent(viewModel: EquipoViewModel, onVolver: () -> Unit) {
        val equipo by viewModel.uiStateEquipo.collectAsState()
        val miembros: List<Usuario>? by viewModel.listaMiembros.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Mi equipo") },
                    navigationIcon = {
                        BotonVolver { onVolver() }
                    }
                )
            }
        ) { paddingValues ->
            Column (modifier = Modifier.padding(paddingValues)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 400.dp), // el min size es el tamanio ancho de cada tarjeta
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(miembros ?: emptyList()) { miembro ->
                        CardMiembro(miembro.idUsuario, miembro.nombre)
                    }
                }
            }

        }
    }

    @Composable
    fun CardMiembro(idUsuario: Long, nombre: String) {
        Column {
            Text("$idUsuario. $nombre")
        }
    }

}