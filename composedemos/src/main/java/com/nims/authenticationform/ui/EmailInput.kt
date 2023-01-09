package com.nims.authenticationform.ui

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.nims.R
import com.nims.authenticationform.ui.Tags.TAG_INPUT_EMAIL

@Composable
fun EmailInput(modifier: Modifier, email: String?, onEmailChanged: (email: String) -> Unit, onNextClicked: () -> Unit) {
    TextField(
        modifier = modifier.testTag(TAG_INPUT_EMAIL),
        value = email ?: "",
        onValueChange = { onEmailChanged(it) },
        label = {
            Text(
                text = stringResource(id = R.string.label_email)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Email, contentDescription = null)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNextClicked()
            }
        )
    )
}