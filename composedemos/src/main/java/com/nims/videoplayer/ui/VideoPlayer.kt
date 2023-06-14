package com.nims.videoplayer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.nims.videoplayer.VideoEvent
import com.nims.videoplayer.model.VideoState

@Composable
fun VideoPlayer(
    videoState: VideoState,
    handleEvent: (event: VideoEvent) -> Unit,
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        val media: MediaItem =
            MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(media)
            addListener(
                object : Player.Listener {
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        if (playbackState == Player.STATE_READY) {
                            handleEvent(VideoEvent.VideoLoaded)
                        }
                    }
                }
            )
        }
    }
}

