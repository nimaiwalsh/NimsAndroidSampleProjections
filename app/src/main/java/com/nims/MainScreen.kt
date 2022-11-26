package com.nims

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun MainScreen(
    openScreen: (route: String) -> Unit,
    modifier: Modifier = Modifier
) {
    MaterialSettingsTheme {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Button(onClick = { openScreen(Screens.SETTINGS_SCREEN) }) {
                Text(text = "Settings screen")
            }
            Button(onClick = { openScreen(Screens.AUTHENTICATION_SCREEN) }) {
                Text(text = "Authentication screen")
            }
            Button(onClick = { openScreen(Screens.HOME_SCREEN) }) {
                Text(text = "Home screen")
            }
            Button(onClick = { openScreen(Screens.EMAIL_INBOX) }) {
                Text(text = "Email inbox")
            }
        }
    }
}