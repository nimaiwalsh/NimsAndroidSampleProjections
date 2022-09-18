package com.nims.authenticationform.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nims.R
import com.nims.authenticationform.model.AuthenticationMode

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    enableAuthentication: Boolean,
    onAuthenticate: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { onAuthenticate() },
        enabled = enableAuthentication,
    ) {
        val buttonText = if (authenticationMode == AuthenticationMode.SIGN_IN) {
            stringResource(id = R.string.action_sign_in)
        } else {
            stringResource(id = R.string.action_sign_up)
        }

        Text(text = buttonText)
    }
}