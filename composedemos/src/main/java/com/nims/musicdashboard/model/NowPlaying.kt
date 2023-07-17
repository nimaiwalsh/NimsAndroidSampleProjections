package com.nims.musicdashboard.model

data class NowPlaying(
    val track: Track,
    val position: Long,
    val state: NowPlayingState,
)

enum class NowPlayingState {
    PLAYING, PAUSED, STOPPED,
}