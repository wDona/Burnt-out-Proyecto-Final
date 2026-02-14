package dev.wdona.burnt_out

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import dev.wdona.burnt_out.viewmodelfactories.TareaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val factory = remember { TareaViewModelFactory(applicationContext) }
            App(factory)
        }
    }
}
