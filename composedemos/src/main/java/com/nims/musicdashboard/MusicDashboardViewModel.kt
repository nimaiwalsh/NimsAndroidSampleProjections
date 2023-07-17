package com.nims.musicdashboard

import androidx.lifecycle.ViewModel
import com.nims.musicdashboard.model.MusicCatalogEvent
import com.nims.musicdashboard.model.MusicDashboardState
import com.nims.musicdashboard.model.ResultStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MusicDashboardViewModel : ViewModel() {

    val uiState = MutableStateFlow(MusicDashboardState())

    fun handleEvent(contentEvent: MusicCatalogEvent) {
        when (contentEvent) {
            is MusicCatalogEvent.RefreshContent -> loadContent()
            else -> {}
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
}