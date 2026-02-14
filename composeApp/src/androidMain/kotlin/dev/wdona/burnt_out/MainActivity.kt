package dev.wdona.burnt_out

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import dev.wdona.burnt_out.viewmodelfactories.EquipoViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.PerfilViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TableroViewModelFactory
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val tareaFactory = remember { TareaViewModelFactory(applicationContext) }
            val equipoViewModelFactory = remember { EquipoViewModelFactory(applicationContext) }
            val perfilViewModelFactory = remember { PerfilViewModelFactory(applicationContext) }
            val tableroViewModelFactory = remember { TableroViewModelFactory(applicationContext) }

            App(tareaFactory, equipoViewModelFactory, perfilViewModelFactory, tableroViewModelFactory)
        }
    }
}
