package dev.wdona.burnt_out.viewmodels

import dev.wdona.burnt_out.shared.BurntOutSDK
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.Tablero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.plus

class TableroViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    private val sdk = BurntOutSDK(databaseDriverFactory)
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiState = MutableStateFlow<Tablero?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiState: StateFlow<Tablero?> = _uiState.asStateFlow()
    private val _listaTableros = MutableStateFlow<List<Tablero>>(emptyList())
    val listaTableros: StateFlow<List<Tablero>> = _listaTableros

    fun crearTablero(idTablero: Long, nombreTablero: String) {
        val tableroLocal = Tablero(idTablero, nombreTablero, 0, 0)

        // Primero offline, luego al servidor
        _listaTableros.value = _listaTableros.value + tableroLocal

        viewModelScope.launch {
            try {
                val tableroServidor = sdk.crearTablero(tableroLocal)


            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

}