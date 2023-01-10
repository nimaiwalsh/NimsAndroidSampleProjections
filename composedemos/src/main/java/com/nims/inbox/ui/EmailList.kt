package com.nims.inbox.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nims.R
import com.nims.Tags
import com.nims.inbox.model.Email

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<Email>,
    onDeleteEmail: (id: String) -> Unit,
) {
    LazyColumn(modifier = modifier.testTag(Tags.EmailInbox.TAG_EMAILS)) {
        items(
            items = emails,
            key = { item -> item.id },
        ) { email ->
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

            val deleteEmailLabel = stringResource(R.string.cd_delete_email)

            SwipeToDismiss(
                // Accessibility allowing user to launch the actions menu for each email item
                // using three-finger tap on the email item.
                modifier = Modifier
                    .semantics {
                        customActions = listOf(
                            CustomAccessibilityAction(deleteEmailLabel) {
                                onDeleteEmail(email.id)
                                true
                            }
                        )
                    }
                    .testTag(Tags.EmailInbox.TAG_EMAIL + email.id),
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd),
                dismissThresholds = { FractionalThreshold(0.15f) },
                dismissContent = {
                    EmailItemForeground(
                        modifier = Modifier
                            .height(emailHeightAnimation)
                            .fillMaxWidth(),
                        email = email,
                        dismissDirection = dismissState.dismissDirection,
                    )
                },
                background = {
                    EmailBackground(
                        modifier = Modifier
                            .height(emailHeightAnimation)
                            .fillMaxWidth(),
                        dismissTargetValue = dismissState.targetValue,
                        dismissCurrentValue = dismissState.currentValue,
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

@Composable
fun EmailItemForeground(
    modifier: Modifier = Modifier,
    email: Email,
    dismissDirection: DismissDirection?,
) {

    val cardElevation = animateDpAsState(
        targetValue = if (dismissDirection != null) 4.dp else 0.dp
    ).value

    Card(
        modifier = modifier.padding(16.dp),
        elevation = cardElevation,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = email.title,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = email.description,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun EmailBackground(
    modifier: Modifier = Modifier,
    dismissTargetValue: DismissValue,
    dismissCurrentValue: DismissValue,
) {
    val backgroundColor by animateColorAsState(
        targetValue = when (dismissTargetValue) {
            DismissValue.DismissedToEnd -> MaterialTheme.colors.error
            else -> MaterialTheme.colors.background
        },
        animationSpec = tween(),
    )

    val iconColor by animateColorAsState(
        targetValue = when (dismissTargetValue) {
            DismissValue.DismissedToEnd -> MaterialTheme.colors.onError
            else -> MaterialTheme.colors.onSurface
        },
        animationSpec = tween()
    )

    val scale by animateFloatAsState(
        targetValue = if (dismissTargetValue == DismissValue.DismissedToEnd) 1f else 0.75f,
        animationSpec = tween()
    )

    Box(
        modifier = modifier
            .background(backgroundColor)
            .padding(horizontal = 20.dp)
    ) {
        // Do not display icon when item is being dismissed
        if (dismissCurrentValue == DismissValue.Default) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .scale(scale),
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.cd_delete_email),
                tint = iconColor,
            )
        }
    }
}