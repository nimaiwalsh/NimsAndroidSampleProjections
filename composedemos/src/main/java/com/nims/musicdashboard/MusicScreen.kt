package com.nims.musicdashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nims.musicdashboard.ui.Dashboard
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun MusicScreen() {
    val viewModel = viewModel<MusicDashboardViewModel>()
    val state by viewModel.uiState.collectAsState()

    MaterialSettingsTheme {
        Dashboard(
            state = state,
            handleEvent = viewModel::handleEvent,
        )
    }
}