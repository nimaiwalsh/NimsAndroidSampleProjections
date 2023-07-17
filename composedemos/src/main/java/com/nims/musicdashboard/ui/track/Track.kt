package com.nims.musicdashboard.ui.track

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nims.musicdashboard.ContentFactory
import com.nims.musicdashboard.model.Track
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun Track(
    modifier: Modifier = Modifier,
    track: Track
) {
    Surface(
        modifier = modifier.semantics(mergeDescendants = true) { },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth()
        ) {
            CoverArt(
                modifier = Modifier.size(40.dp),
                track = track
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = track.title,
                    fontSize = 16.sp
                )
                Text(
                    text = track.artist,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_Track() {
    MaterialSettingsTheme {
        Track(
            modifier = Modifier.fillMaxWidth(),
            track = ContentFactory.makeTrack()
        )
    }
}