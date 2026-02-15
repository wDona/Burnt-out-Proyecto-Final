package dev.wdona.burnt_out.viewmodels

import dev.wdona.burnt_out.shared.BurntOutSDK
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.Tablero
import dev.wdona.burnt_out.shared.network.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PerfilViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    private val sdk = BurntOutSDK(databaseDriverFactory)
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiState = MutableStateFlow<Usuario?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiState: StateFlow<Usuario?> = _uiState.asStateFlow()
    private val _listaUsuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val listaUsuarios: StateFlow<List<Usuario>> = _listaUsuarios
}