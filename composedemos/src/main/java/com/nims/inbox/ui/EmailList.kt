package com.nims.inbox.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.nims.inbox.model.Email

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<Email>,
    onDeleteEmail: (id: String) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(items = emails, key = { item -> item.id }) { email ->
            // dismissal flag indicating to complete animation before removing email from list
            var isEmailItemDismissed by remember { mutableStateOf(false) }

            // Triggered whenever an email is swiped right to dismiss
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd) {
                        isEmailItemDismissed = true
                    }
                    true
                }
            )

            val emailHeightAnimation by animateDpAsState(
                targetValue = if (isEmailItemDismissed) 0.dp else 120.dp,
                // delay the start of animation to allow swipe to occur before hand
                animationSpec = tween(delayMillis = 300),
                // update our Inbox state and remove the email - we won’t experience any jank here,
                // as the animation would have been completed.
                finishedListener = { onDeleteEmail(email.id) }
            )

            val dividerVisibilityAnimation by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default) 1f else 0f,
                animationSpec = tween(delayMillis = 300)
            )

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = { FractionalThreshold(0.15f) },
                dismissContent = {
                    EmailItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(emailHeightAnimation),
                        email = email,
                        dismissState = dismissState,
                    )
                },
                background = {
                    EmailBackground(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(emailHeightAnimation),
                        dismissState = dismissState,
                    )
                }
            )

            Divider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .alpha(dividerVisibilityAnimation)
            )
        }
    }
}