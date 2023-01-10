package com.nims.inbox.ui

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.nims.Tags

@Composable
fun Loading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.testTag(Tags.EmailInbox.TAG_LOADING)
    )
}