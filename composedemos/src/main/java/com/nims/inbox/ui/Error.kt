package com.nims.inbox.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nims.R
import com.nims.Tags

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier
            .testTag(Tags.EmailInbox.TAG_ERROR)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.message_content_error))

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { onTryAgain() }) {
            Text(text = stringResource(id = R.string.label_try_again))
        }
    }
}