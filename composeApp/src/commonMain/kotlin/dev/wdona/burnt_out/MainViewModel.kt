package dev.wdona.burnt_out

import dev.wdona.burnt_out.shared.BurntOutSDK
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.RespuestaTest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainViewModel(databaseDriverFactory: DatabaseDriverFactory) {
    private val sdk = BurntOutSDK(databaseDriverFactory)
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow<RespuestaTest?>(null)
    val uiState: StateFlow<RespuestaTest?> = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            try {
                _uiState.value = sdk.obtenerDatosDePrueba(RespuestaTest("Esto es una prueba", "Esto es el cuerpo de la prueba"))
            } catch (e: Exception) {
                // Manejar el error apropiadamente
                println("Error recuperando la informacion: ${e.message}")
            }
        }
    }
}
