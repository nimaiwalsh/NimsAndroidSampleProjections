package com.nims.musicdashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nims.musicdashboard.model.MusicCatalogEvent
import com.nims.musicdashboard.model.MusicDashboardState
import com.nims.musicdashboard.model.NowPlayingState
import com.nims.musicdashboard.model.ResultStatus
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MusicDashboardViewModel : ViewModel() {

    val uiState = MutableStateFlow(MusicDashboardState())
    var nowPlayingFlow: Job? = null

    override fun onCleared() {
        nowPlayingFlow?.cancel()
        super.onCleared()
    }

    fun handleEvent(contentEvent: MusicCatalogEvent) {
        when (contentEvent) {
            is MusicCatalogEvent.RefreshContent -> loadContent()
            is MusicCatalogEvent.PlayTrack -> {
                uiState.update {
                    it.copy(nowPlaying = ContentFactory.makeNowPlaying(contentEvent.track))
                }
                playMusic()
            }

            is MusicCatalogEvent.SeekTrack -> {
                uiState.update {
                    it.copy(
                        nowPlaying = it.nowPlaying?.copy(position = contentEvent.position.toLong())
                    )
                }
            }

            is MusicCatalogEvent.Search -> performSearch(contentEvent.searchTerm)
            MusicCatalogEvent.ClearSearchQuery -> uiState.update { it.copy(searchTerm = null) }
            MusicCatalogEvent.RewindNowPlaying -> rewindMusic()
            MusicCatalogEvent.FastForwardNowPlaying -> fastForwardMusic()
            MusicCatalogEvent.ToggleNowPlayingState -> toggleNowPlaying()
        }
    }

    private fun loadContent() {
        val data = ContentFactory.makeContentList()
        uiState.update {
            it.copy(
                status = ResultStatus.SUCCESS,
                tracks = data,
                nowPlaying = ContentFactory.makeNowPlaying(data[0])
            )
        }
    }

    private fun performSearch(searchTerm: String) {
        val results = uiState.value.tracks.filter {
            it.title.contains(
                searchTerm,
                ignoreCase = true
            ) ||
                    it.artist.contains(
                        searchTerm,
                        ignoreCase = true
                    )
        }
        uiState.update {
            it.copy(
                searchTerm = searchTerm,
                searchResults = results
            )
        }
    }

    private fun toggleNowPlaying() {
        if (uiState.value.nowPlaying?.state == NowPlayingState.PLAYING) {
            pauseMusic()
        } else {
            playMusic()
        }
    }

    private fun rewindMusic() {
        uiState.value.nowPlaying?.let { nowPlaying ->
            val newPosition = nowPlaying.position - 10

            uiState.update {
                it.copy(
                    nowPlaying = nowPlaying.copy(
                        position = if (newPosition < 0) 0 else newPosition
                    )
                )
            }
        }
    }

    private fun fastForwardMusic() {
        uiState.value.nowPlaying?.let { nowPlaying ->
            val newPosition = nowPlaying.position + 10
            val maxLength = nowPlaying.position

            uiState.update {
                it.copy(
                    nowPlaying = nowPlaying.copy(
                        position = if (newPosition > nowPlaying.position) maxLength else newPosition
                    )
                )
            }
        }
    }

    private fun pauseMusic() {
        nowPlayingFlow?.cancel()

        uiState.update {
            it.copy(
                nowPlaying = it.nowPlaying?.copy(state = NowPlayingState.PAUSED)
            )
        }
    }

    // Simulate playing music for compose demo
    private fun playMusic() {
        nowPlayingFlow?.cancel()
        val nowPlaying = uiState.value.nowPlaying!!

        uiState.update {
            it.copy(
                nowPlaying = nowPlaying.copy(state = NowPlayingState.PLAYING)
            )
        }

        nowPlayingFlow = viewModelScope.launch {
            while (isActive) {
                val maxLength = uiState.value.nowPlaying!!.track.length
                val newPosition = uiState.value.nowPlaying!!.position + 1

                if (newPosition >= maxLength) {
                    uiState.update {
                        it.copy(
                            nowPlaying = nowPlaying.copy(
                                state = NowPlayingState.STOPPED,
                                position = 0
                            )
                        )
                    }
                    cancel()
                } else {
                    uiState.update {
                        it.copy(
                            nowPlaying = nowPlaying.copy(
                                state = NowPlayingState.PLAYING,
                                position = newPosition
                            )
                        )
                    }
                }
                delay(1000)
            }
        }
    }
}