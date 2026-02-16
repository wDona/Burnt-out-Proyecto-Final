package dev.wdona.burnt_out.shared

import dev.wdona.burnt_out.Equipo
import dev.wdona.burnt_out.shared.cache.AppDatabase
import dev.wdona.burnt_out.shared.cache.DatabaseDriverFactory
import dev.wdona.burnt_out.shared.network.KtorClient
import dev.wdona.burnt_out.Tablero
import dev.wdona.burnt_out.Tarea
import dev.wdona.burnt_out.Usuario
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

    fun obtenerTareasPorTableroLocal(idTablero: Long): List<Tarea> {
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

    fun obtenerTablerosPorOrganizacionLocal(idOrganizacion: Long): List<Tablero> {
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

    fun obtenerMiembrosDeEquipoLocal(idEquipo: Long): List<Usuario> {
        return appDatabase.appDatabaseQueries.getUsuariosByEquipo(idEquipo).executeAsList()
            .map {
                Usuario(
                    idUsuario = it.ID_Usuario,
                    nombre = it.Nombre,
                    username = it.Username,
                    password = "",
                    idOrganizacion = it.FK_ID_Organizacion,
                    idEquipo = idEquipo,
                    riesgoBurnout = it.Riesgo_Burnout,
                    descripcion = it.Descripcion


                )
            }
    }

    suspend fun crearEquipo(equipo: Equipo): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                appDatabase.appDatabaseQueries.insertEquipo(
                    equipo.titulo,
                    equipo.idOrganizacion
                )
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    fun obtenerEquiposPorOrganizacionLocal(idOrganizacion: Long): List<Equipo> {
        return appDatabase.appDatabaseQueries.getEquiposByOrg(idOrganizacion).executeAsList()
            .map { equipo ->
                Equipo(
                    idEquipo = equipo.ID_Equipo,
                    titulo = equipo.Titulo,
                    idOrganizacion = equipo.FK_ID_Org,
                    puntuacion = equipo.Puntuacion,
                    idMiembros = obtenerMiembrosDeEquipoLocal(equipo.ID_Equipo).map { miembro -> miembro.idUsuario }
                )
            }
    }

    fun obtenerEquipoPorIdLocal(idEquipo: Long): Equipo? {
        val equipo = appDatabase.appDatabaseQueries.getEquipoById(idEquipo).executeAsOneOrNull()
        return equipo?.let {
            Equipo(
                idEquipo = it.ID_Equipo,
                titulo = it.Titulo,
                idOrganizacion = it.FK_ID_Org,
                puntuacion = it.Puntuacion,
                idMiembros = obtenerMiembrosDeEquipoLocal(it.ID_Equipo).map { miembro -> miembro.idUsuario }
            )
        }
    }

}