package com.nims.musicdashboard.ui.track

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nims.musicdashboard.ContentFactory
import com.nims.musicdashboard.model.Track
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun CoverArt(
    modifier: Modifier = Modifier,
    track: Track
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        track.cover
                    )
                ),
                shape = RoundedCornerShape(4.dp)
            )
    )
}

@Preview
@Composable
fun Preview_CoverArt() {
    MaterialSettingsTheme {
        CoverArt(
            modifier = Modifier.size(50.dp),
            track = ContentFactory.makeTrack()
        )
    }
}