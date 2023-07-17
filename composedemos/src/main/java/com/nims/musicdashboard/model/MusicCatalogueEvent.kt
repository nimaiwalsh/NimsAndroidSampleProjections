package com.nims.musicdashboard.model

sealed interface MusicCatalogEvent {

    object RefreshContent : MusicCatalogEvent

    data class PlayTrack(val track: Track) : MusicCatalogEvent

    data class SeekTrack(val position: Float) : MusicCatalogEvent

    object RewindNowPlaying : MusicCatalogEvent

    object ToggleNowPlayingState : MusicCatalogEvent

    object FastForwardNowPlaying : MusicCatalogEvent

    data class Search(val searchTerm: String) : MusicCatalogEvent

    object ClearSearchQuery : MusicCatalogEvent
}