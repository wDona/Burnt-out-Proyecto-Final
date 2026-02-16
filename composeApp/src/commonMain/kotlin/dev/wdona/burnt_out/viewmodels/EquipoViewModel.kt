package dev.wdona.burnt_out.viewmodels

import dev.wdona.burnt_out.Equipo
import dev.wdona.burnt_out.Usuario
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
    private val _uiStateEquipo = MutableStateFlow<Equipo?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiStateEquipo: StateFlow<Equipo?> = _uiStateEquipo.asStateFlow()
    private val _listaEquipos = MutableStateFlow<List<Equipo>>(emptyList())
    val listaEquipos: StateFlow<List<Equipo>> = _listaEquipos

    private val _uiStateMiembro = MutableStateFlow<Usuario?>(null)
    val uiStateMiembro: StateFlow<Usuario?> =_uiStateMiembro.asStateFlow()
    private val _listaMiembros = MutableStateFlow<List<Usuario>?>(emptyList())
    val listaMiembros: StateFlow<List<Usuario>?> = _listaMiembros


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

    fun cargarEquipoPorId(idEquipo: Long) : Equipo? {
        viewModelScope.launch {
            try {
                val equipo = sdk.obtenerEquipoPorIdLocal(idEquipo)
                _uiStateEquipo.value = equipo
            } catch (e: Exception) {
                println("Error cargando equipo: ${e.message}")
            }
        }
        return _uiStateEquipo.value
    }

    fun cargarMiembrosEquipo(idEquipo: Long) {
        viewModelScope.launch {
            try {
                val miembros = sdk.obtenerMiembrosDeEquipoLocal(idEquipo)
                _listaMiembros.value = miembros
            } catch (e: Exception) {
                println("Error cargando miembros del equipo: ${e.message}")
            }
        }
    }
}