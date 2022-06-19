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
    openSettingsScreen: (route: String) -> Unit,
    modifier: Modifier = Modifier
) {
    MaterialSettingsTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Button(onClick = { openSettingsScreen(Screens.SETTINGS_SCREEN) }) {
                Text(text = "Settings screen")
            }
        }
    }
}