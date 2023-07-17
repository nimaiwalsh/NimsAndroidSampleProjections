package com.nims

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nims.authenticationform.AuthenticationScreen
import com.nims.home.HomeScreen
import com.nims.inbox.InboxScreen
import com.nims.musicdashboard.MusicScreen
import com.nims.settings.SettingsScreen
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

            fun popBackstack() {
                navController.popBackStack()
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
                            openScreen = { route -> navigate(route) },
                        )
                    }

                    /** Screen showcasing a composable settings screen */
                    composable(Screens.SETTINGS_SCREEN) {
                        SettingsScreen(
                            popBackstack = { popBackstack() }
                        )
                    }

                    /** Screen showcasing a composable log-in/sign-up form screen */
                    composable(Screens.AUTHENTICATION_SCREEN) {
                        AuthenticationScreen()
                    }

                    /** Screen showcasing a home layout with bottom navigation */
                    composable(Screens.HOME_SCREEN) {
                        HomeScreen()
                    }

                    /** Screen showcasing email inbox with bottom navigation */
                    composable(Screens.EMAIL_INBOX) {
                        InboxScreen()
                    }

                    /** Screen showcasing music dashboard */
                    composable(Screens.MUSIC_DASHBOARD) {
                        MusicScreen()
                    }
                }
            }
        }
    }
}

object Screens {
    const val MAIN_SCREEN = "MAIN_SCREEN"
    const val SETTINGS_SCREEN = "SETTINGS_SCREEN"
    const val AUTHENTICATION_SCREEN = "AUTHENTICATION_SCREEN"
    const val HOME_SCREEN = "HOME_SCREEN"
    const val EMAIL_INBOX = "EMAIL_INBOX"
    const val MUSIC_DASHBOARD = "MUSIC_DASHBOARD"
}