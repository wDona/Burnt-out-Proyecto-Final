package dev.wdona.burnt_out.viewmodels

import dev.wdona.burnt_out.shared.BurntOutSDK
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.Tarea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
        val tareaLocal = Tarea(tarea, descripcion)

        // Primero offline, luego al servidor
        _listaComponentes.value = _listaComponentes.value + tareaLocal

        viewModelScope.launch {
            try {
                val tareaServidor = sdk.postTarea(tareaLocal)

                _listaComponentes.value = _listaComponentes.value.map {
                    if (it.subject == tareaLocal.subject && it.cuerpo == tareaLocal.cuerpo) {
                        tareaServidor ?: it
                    } else {
                        it
                    }
                }
            } catch (e: Exception) {

                println("Error: ${e.message}")
            }
        }
    }
}