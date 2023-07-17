package com.nims.musicdashboard.ui

import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import com.nims.musicdashboard.model.MusicCatalogEvent
import com.nims.musicdashboard.model.MusicDashboardState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dashboard(
    state: MusicDashboardState,
    handleEvent: (contentEvent: MusicCatalogEvent) -> Unit,
) {

    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

    BackdropScaffold(
        appBar = {  },
        backLayerContent = {  },
        frontLayerContent = {  },
        scaffoldState = scaffoldState,
        backLayerBackgroundColor = MaterialTheme.colors.surface
    ) {
    }
}