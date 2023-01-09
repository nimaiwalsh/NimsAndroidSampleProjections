package com.nims.authenticationform.model

/**
 * Represents the different ui events (actions) this view model can perform to change to our state.
 * These events are triggered from our composable UI.
 */
sealed class AuthenticationEvent {
    object ToggleAuthenticationMode: AuthenticationEvent()
    class EmailChanged(val emailAddress: String) : AuthenticationEvent()
    class PasswordChanged(val password: String) : AuthenticationEvent()
    object Authentication: AuthenticationEvent()
    object ErrorDismissed: AuthenticationEvent()
}