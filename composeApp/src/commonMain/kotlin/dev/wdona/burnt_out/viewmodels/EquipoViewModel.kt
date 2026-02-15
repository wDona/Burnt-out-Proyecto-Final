package dev.wdona.burnt_out.viewmodels

import dev.wdona.burnt_out.Equipo
import dev.wdona.burnt_out.shared.BurntOutSDK
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquipoViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    private val sdk = BurntOutSDK(databaseDriverFactory)
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiState = MutableStateFlow<Equipo?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiState: StateFlow<Equipo?> = _uiState.asStateFlow()
    private val _listaEquipos = MutableStateFlow<List<Equipo>>(emptyList())
    val listaEquipos: StateFlow<List<Equipo>> = _listaEquipos


    fun crearEquipo(idEquipo: Long, nombreEquipo: String, idOrganizacion: Long) {
        val equipoLocal = Equipo(idEquipo, nombreEquipo, 0, idOrganizacion, emptyList())

        // Primero offline, luego al servidor
        _listaEquipos.value = _listaEquipos.value + equipoLocal

        viewModelScope.launch {
            try {
                val equipoLocal = sdk.crearEquipo(equipoLocal)


            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    fun cargarEquiposPorOrganizacion(idOrganizacion: Long) {
        viewModelScope.launch {
            try {
                val equipos = sdk.obtenerEquiposPorOrganizacionLocal(idOrganizacion)
                _listaEquipos.value = equipos
            } catch (e: Exception) {
                println("Error cargando tareas: ${e.message}")
            }
        }
    }
}