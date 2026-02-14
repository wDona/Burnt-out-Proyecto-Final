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

class TareaViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    private val sdk = BurntOutSDK(databaseDriverFactory)
    // Crea un CoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    // Crea un MutableStateFlow (para actualizar el estado en la UI) privado y mutable para la respuesta
    private val _uiState = MutableStateFlow<Tarea?>(null)

    // Crea un StateFlow publico y de solo lectura para la respuesta
    val uiState: StateFlow<Tarea?> = _uiState.asStateFlow()
    private val _listaTareas = MutableStateFlow<List<Tarea>>(emptyList())
    val listaTareas: StateFlow<List<Tarea>> = _listaTareas


    fun crearTarea(idTarea: Int, nombreTarea: String, descripcion: String, idTablero: Int, ) {
        val tareaLocal = Tarea(idTarea, nombreTarea, descripcion, null, idTablero, null, null)

        // Primero offline, luego al servidor
        _listaTareas.value = _listaTareas.value + tareaLocal

        viewModelScope.launch {
            try {
                val tareaServidor = sdk.postTarea(tareaLocal)

                // Modifica la lista entera por el contenido que se devuelva uno a uno
                _listaTareas.value = _listaTareas.value.map {
                    if (it.titulo == tareaLocal.titulo && it.descripcion == tareaLocal.descripcion) {
                        tareaServidor
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