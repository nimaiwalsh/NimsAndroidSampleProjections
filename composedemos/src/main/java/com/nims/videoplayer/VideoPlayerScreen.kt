package com.nims.videoplayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nims.videoplayer.ui.VideoPlayer

@Composable
fun VideoPlayerScreen() {
    val viewModel: VideoPlayerViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    VideoPlayer(
        videoState = uiState,
        handleEvent = viewModel::handleEvent
    )
}