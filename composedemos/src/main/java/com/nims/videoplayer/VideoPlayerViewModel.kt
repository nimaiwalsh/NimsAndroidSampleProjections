package com.nims.videoplayer

import androidx.lifecycle.ViewModel
import com.nims.videoplayer.model.PlayerStatus
import com.nims.videoplayer.model.VideoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VideoPlayerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VideoState())
    val uiState: StateFlow<VideoState> get() = _uiState.asStateFlow()


    fun handleEvent(videoEvent: VideoEvent) {
        when (videoEvent) {
            VideoEvent.VideoLoaded -> {
                _uiState.value = _uiState.value.copy(playerStatus = PlayerStatus.IDLE)
            }
            VideoEvent.ToggleStatus -> togglePlayerStatus()
            VideoEvent.VideoError -> {
                _uiState.value = _uiState.value.copy(playerStatus = PlayerStatus.ERROR)
            }
        }
    }

    private fun togglePlayerStatus() {
        val playerStatus = _uiState.value.playerStatus
        val newPlayStatus =
            if (playerStatus != PlayerStatus.PLAYING) PlayerStatus.PLAYING else PlayerStatus.PAUSED
        _uiState.value = _uiState.value.copy(playerStatus = newPlayStatus)
    }
}

sealed class VideoEvent {
    object ToggleStatus : VideoEvent()
    object VideoLoaded : VideoEvent()
    object VideoError : VideoEvent()
}