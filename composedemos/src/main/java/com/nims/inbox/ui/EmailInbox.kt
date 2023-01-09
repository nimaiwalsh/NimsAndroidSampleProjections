package com.nims.inbox.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nims.R
import com.nims.inbox.model.InboxEvent
import com.nims.inbox.model.InboxState
import com.nims.inbox.model.InboxStatus

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EmailInbox(
    modifier: Modifier = Modifier,
    inboxState: InboxState,
    handleEvent: (inboxEvent: InboxEvent) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    text = when (inboxState.status) {
                        InboxStatus.EMPTY,
                        InboxStatus.ERROR -> stringResource(R.string.title_empty)
                        InboxStatus.HAS_EMAILS,
                        InboxStatus.LOADING,
                        -> {
                            stringResource(
                                id = R.string.title_inbox,
                                inboxState.emails?.count() ?: 0
                            )
                        }
                    }
                )
            }
        }
    ) {
        // House main content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {

            when (inboxState.status) {
                InboxStatus.LOADING -> Loading()
                InboxStatus.HAS_EMAILS -> EmailList(
                    emails = inboxState.emails ?: emptyList(),
                    onDeleteEmail = { id -> handleEvent(InboxEvent.DeleteEmail(id)) })
                InboxStatus.ERROR -> ErrorState(
                    onTryAgain = { handleEvent(InboxEvent.RefreshContent) }
                )
                InboxStatus.EMPTY -> EmptyState(
                    onRefresh = { handleEvent(InboxEvent.RefreshContent) }
                )
            }
        }

    }
}