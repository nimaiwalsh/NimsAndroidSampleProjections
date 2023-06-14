package com.nims.videoplayer.ui

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.nims.videoplayer.model.PlayerStatus

@Composable
fun Playback(
    modifier: Modifier = Modifier,
    state: PlayerStatus = PlayerStatus.IDLE,
    exoPlayer: ExoPlayer,
    context: Context,
) {

    /**
     * side-effect allows us to execute a function based on the scope of the composable,
     * launching a coroutine to execute the provided block.
     * When composition happens,
     * the side-effect block will be executed and if the LaunchedEffect leaves the composition,
     * then the coroutine will be cancelled.
     * Subsequent compositions will only trigger the side-effect block if the provided key is changed”
     */
    LaunchedEffect(
        key1 = exoPlayer,
        block = { exoPlayer.prepare() }
    )

    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                PlayerView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    // we want to create our own UI controls
                    hideController()
                    useController = false

                    player = exoPlayer
                }
            },
            update = { playerView ->
                when (state) {
                    PlayerStatus.PLAYING -> playerView.player?.play()
                    PlayerStatus.PAUSED -> playerView.player?.pause()
                    else -> {
                        // do nothing
                    }
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}