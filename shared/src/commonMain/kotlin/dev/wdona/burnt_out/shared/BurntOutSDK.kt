package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorClient
import dev.wdona.burnt_out.Tablero
import dev.wdona.burnt_out.Tarea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BurntOutSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val appDatabase = AppDatabase(databaseDriverFactory.createDriver())
    private val api = KtorClient()

    @Throws(Exception::class)
    private suspend fun _postTarea(tarea: Tarea): Tarea {
        return api.enviar(tarea)
    }

    private suspend fun _dbAddTarea(tarea: Tarea): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                appDatabase.appDatabaseQueries.insertTarea(
                    tarea.titulo,
                    tarea.descripcion,
                    tarea.estado,
                    tarea.idTableroPerteneciente.toLong(),
                    tarea.idUsuarioAsignado,
                )
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun _dbAddTablero(tablero: Tablero): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                appDatabase.appDatabaseQueries.insertTablero(
                    tablero.titulo,
                    tablero.idEquipo,
                    tablero.idOrganizacion
                )
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    private suspend fun _postTablero(tablero: Tablero): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                api.enviar(tablero)
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    suspend fun crearTarea(tarea: Tarea): Boolean {
        _dbAddTarea(tarea)
//        try {
//            _postTarea(tarea)
//        } catch (e: Exception) {
//
//            println("Error al enviar al servidor: ${e.message}")
//            return false
//        }
        return true
    }

    suspend fun crearTablero(tablero: Tablero): Boolean {
        _dbAddTablero(tablero)
//        try {
//            _postTablero(tablero)
//        } catch (e: Exception) {
//
//            println("Error al enviar al servidor: ${e.message}")
//            return false
//        }
        return true
    }

    suspend fun obtenerTareasPorTableroLocal(idTablero: Long): List<Tarea> {
        return appDatabase.appDatabaseQueries.getTareasByTablero(idTablero).executeAsList()
            .map {
                Tarea(
                    idTarea = it.ID_Tarea,
                    titulo = it.Titulo,
                    descripcion = it.Descripcion,
                    estado = it.Estado,
                    idTableroPerteneciente = it.FK_ID_Tabl,
                    idUsuarioAsignado = it.FK_ID_Usuario,
                    idSubtareas = listOf()
                )
            }
    }

    suspend fun obtenerTablerosPorOrganizacionLocal(idOrganizacion: Long): List<Tablero> {
        return appDatabase.appDatabaseQueries.getTablerosByOrg(idOrganizacion).executeAsList()
            .map {
                Tablero(
                    idTablero = it.ID_Tabl,
                    titulo = it.Titulo,
                    idOrganizacion = it.FK_ID_Org,
                    idEquipo = it.FK_ID_Equipo
                )
            }
    }

}