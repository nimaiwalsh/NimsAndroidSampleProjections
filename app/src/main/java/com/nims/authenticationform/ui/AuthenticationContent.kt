package com.nims.authenticationform.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nims.authenticationform.model.AuthenticationEvent
import com.nims.authenticationform.model.AuthenticationState

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
            CircularProgressIndicator()
        } else {
            AuthenticationForm(
                authenticationMode = authenticationState.authenticationMode,
                email = authenticationState.email,
                password = authenticationState.password,
                onEmailChanged = { handleEvent(AuthenticationEvent.EmailChanged(it)) },
                onPasswordChanged = { handleEvent(AuthenticationEvent.PasswordChanged(it)) },
                onAuthenticate = { handleEvent(AuthenticationEvent.Authentication) },
            )
        }
    }
}