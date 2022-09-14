package com.nims.authenticationform

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nims.authenticationform.ui.AuthenticationContent
import com.nims.ui.theme.MaterialSettingsTheme

/** Entry point to Authentication Screen, no arguments supplied. */
@Composable
fun AuthenticationScreen() {

    val viewModel: AuthenticationViewModel = viewModel()

    MaterialSettingsTheme {
        AuthenticationContent(
            modifier = Modifier.fillMaxSize(),
            authenticationState = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent,
        )
    }
}