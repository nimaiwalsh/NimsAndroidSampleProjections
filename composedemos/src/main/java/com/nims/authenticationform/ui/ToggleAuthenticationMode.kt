package com.nims.authenticationform.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nims.R
import com.nims.Tags
import com.nims.authenticationform.model.AuthenticationMode

@Composable
fun ToggleAuthenticationMode(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    onToggleAuthentication: () -> Unit
) {
    /**
     * The Surface composable will compose the provided body inside of a Box composable.
     * It will also theme itself use the surface color from the application theme,
     * which is what we want to be applied to our settings item in terms of styling.
     * This saves us from using a Box composable and applying a collection of styling ourselves
     * when this component already exists to do it for us.
     */
    Surface(
        modifier = modifier.padding(top = 16.dp),
        elevation = 8.dp
    ) {
        TextButton(
            modifier = Modifier.testTag(Tags.AuthenticationForm.TAG_AUTHENTICATION_TOGGLE),
            onClick = { onToggleAuthentication() }
        ) {
            val buttonText = if (authenticationMode == AuthenticationMode.SIGN_IN) {
                stringResource(id = R.string.action_need_account)
            } else {
                stringResource(id = R.string.action_already_have_account)
            }

            Text(text = buttonText)
        }
    }
}