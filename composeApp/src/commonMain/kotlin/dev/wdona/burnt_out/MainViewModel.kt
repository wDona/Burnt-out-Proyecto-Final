package dev.wdona.burnt_out

import dev.wdona.burnt_out.shared.BurntOutSDK
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    private val sdk = BurntOutSDK(databaseDriverFactory)
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiState = MutableStateFlow<Tarea?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiState: StateFlow<Tarea?> = _uiState.asStateFlow()
    private val _listaComponentes = MutableStateFlow<List<Tarea>>(emptyList())
    val listaComponentes: StateFlow<List<Tarea>> = _listaComponentes


    fun enviarTarea(tarea: String, descripcion: String) {
        // Ejecuta la corrutina para no bloquear el hilo principal haciendo la peticion
        viewModelScope.launch {
            try {
                _uiState.value = sdk.postTarea(Tarea(tarea, descripcion))
                _listaComponentes.value = _listaComponentes.value + (_uiState.value ?: Tarea("", ""))
            } catch (e: Exception) {
                println("Error recuperando la informacion: ${e.message}")
            }
        }
    }

    fun limpiarUiState() {
        _uiState.value = null
    }
}
