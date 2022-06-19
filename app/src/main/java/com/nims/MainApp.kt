package com.nims

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nims.settings.ui.SettingsScreen
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
@ExperimentalMaterialApi
fun MainApp() {
    MaterialSettingsTheme {
        Surface(color = MaterialTheme.colors.background) {

            val navController = rememberNavController()

            fun navigate(route: String) {
                navController.navigate(route) {
                    launchSingleTop = true
                }
            }

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
            ) { innerPaddingModifier ->
                NavHost(
                    navController = navController,
                    startDestination = Screens.MAIN_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {

                    composable(Screens.MAIN_SCREEN) {
                        MainScreen(
                            openSettingsScreen = { route -> navigate(route) }
                        )
                    }

                    composable(Screens.SETTINGS_SCREEN) {
                        SettingsScreen()
                    }
                }
            }
        }
    }
}

object Screens {
    const val MAIN_SCREEN = "MAIN_SCREEN"
    const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
}