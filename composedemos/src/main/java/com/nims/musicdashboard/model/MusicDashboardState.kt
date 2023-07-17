package com.nims.musicdashboard.model

data class MusicDashboardState(
    val status: ResultStatus = ResultStatus.LOADING,
    val tracks: List<Track> = emptyList(),
    val nowPlaying: NowPlaying? = null,
    val searchTerm: String? = null,
    val searchResults: List<Track> = emptyList(),
) {
    fun newTracks() = tracks.filter { it.isNew }
    fun featuredTrack() = tracks.filter { it.isFeatured }
    fun libraryTracks() = tracks.filter { !it.isFeatured && !it.isNew }
}