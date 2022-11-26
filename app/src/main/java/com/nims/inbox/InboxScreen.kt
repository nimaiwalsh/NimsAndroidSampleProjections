package com.nims.inbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nims.inbox.ui.EmailInbox
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun InboxScreen() {
    val viewModel: InboxViewModel = viewModel()
    MaterialSettingsTheme {
        EmailInbox(
            modifier = Modifier.fillMaxWidth(),
            inboxState = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
    LaunchedEffect(Unit) {
        viewModel.loadContent()
    }
}