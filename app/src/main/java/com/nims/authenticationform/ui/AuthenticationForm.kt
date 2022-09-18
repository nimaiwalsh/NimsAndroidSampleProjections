package com.nims.authenticationform.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.nims.authenticationform.model.AuthenticationEvent
import com.nims.authenticationform.model.AuthenticationMode
import com.nims.authenticationform.model.PasswordRequirements

/** The parent container to our sign-in/sign-up form*/
@Composable
fun AuthenticationForm(
    modifier: Modifier = Modifier,
    authenticationMode: AuthenticationMode,
    email: String?,
    password: String?,
    completedPasswordRequirements: List<PasswordRequirements>,
    enableAuthentication: Boolean,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onAuthenticate: () -> Unit,
    onToggleAuthenticationMode: () -> Unit,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(32.dp))
        AuthenticationTitle(authenticationMode = authenticationMode)
        Spacer(modifier = Modifier.height(40.dp))

        // used to focus on the password field after clicking IMEAction 'next' from email input field
        val passwordFocusRequester = FocusRequester.Default

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailInput(
                    modifier = Modifier.fillMaxWidth(),
                    email = email ?: "",
                    onEmailChanged = onEmailChanged,
                    onNextClicked = { passwordFocusRequester.requestFocus() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordInput(
                    modifier = Modifier.fillMaxWidth(),
                    password = password ?: "",
                    onPasswordChanged = onPasswordChanged,
                    passwordFocusRequester = passwordFocusRequester,
                    onDoneClicked = onAuthenticate
                )

                Spacer(modifier = Modifier.height(12.dp))

                AnimatedVisibility(visible = authenticationMode == AuthenticationMode.SIGN_UP) {
                    PasswordRequirements(
                        modifier = Modifier.fillMaxWidth(),
                        satisfiedRequirements = completedPasswordRequirements
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                AuthenticationButton(
                    modifier = Modifier.fillMaxWidth(),
                    authenticationMode = authenticationMode,
                    enableAuthentication = enableAuthentication,
                    onAuthenticate = onAuthenticate,
                )
            }
        }

        Spacer(modifier = Modifier.weight(1F))

        ToggleAuthenticationMode(
            modifier = Modifier.fillMaxWidth(),
            authenticationMode = authenticationMode,
            onToggleAuthentication = onToggleAuthenticationMode,
        )
    }
}