package dev.wdona.burnt_out

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import dev.wdona.burnt_out.pantallas.MenuInicio
import dev.wdona.burnt_out.theme.CustomMaterialTheme
import dev.wdona.burnt_out.theme.DarkColorScheme
import dev.wdona.burnt_out.theme.LightColorScheme
import dev.wdona.burnt_out.viewmodels.MainViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(factory: MainViewModelFactory) {
    CustomMaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = if (isSystemInDarkTheme()) DarkColorScheme.background else LightColorScheme.background

        ) {
            Navigator(MenuInicio(factory))
        }
    }
}






