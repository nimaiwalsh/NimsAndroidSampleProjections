package com.nims.authenticationform.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.nims.authenticationform.model.AuthenticationEvent
import com.nims.authenticationform.model.AuthenticationState
import com.nims.Tags.AuthenticationForm.TAG_CONTENT
import com.nims.Tags.AuthenticationForm.TAG_PROGRESS

@Composable
fun AuthenticationContent(
    modifier: Modifier = Modifier,
    authenticationState: AuthenticationState,
    handleEvent: (event: AuthenticationEvent) -> Unit,
) {
    /**
     *  Box composable provides support for the alignment of child composables,
     *  as well as the ability to show overlapping composables - which makes it perfect for what we need.
     */
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (authenticationState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag(TAG_PROGRESS)
            )
        } else {
            // Authentication form
            AuthenticationForm(
                authenticationMode = authenticationState.authenticationMode,
                enableAuthentication = authenticationState.isFormValid(),
                email = authenticationState.email,
                password = authenticationState.password,
                completedPasswordRequirements = authenticationState.passwordRequirements,
                onEmailChanged = { handleEvent(AuthenticationEvent.EmailChanged(it)) },
                onPasswordChanged = { handleEvent(AuthenticationEvent.PasswordChanged(it)) },
                onAuthenticate = { handleEvent(AuthenticationEvent.Authentication) },
                onToggleAuthenticationMode = { handleEvent(AuthenticationEvent.ToggleAuthenticationMode) }
            )
            // errorDialog
            authenticationState.error?.let { error ->
                AuthenticationErrorDialog(
                    error = error,
                    dismissError = { handleEvent(AuthenticationEvent.ErrorDismissed) })
            }
        }
    }
}