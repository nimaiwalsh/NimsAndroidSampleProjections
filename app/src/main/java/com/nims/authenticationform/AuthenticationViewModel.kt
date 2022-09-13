package com.nims.authenticationform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nims.authenticationform.model.AuthenticationEvent
import com.nims.authenticationform.model.AuthenticationMode
import com.nims.authenticationform.model.AuthenticationState
import com.nims.authenticationform.model.PasswordRequirements
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthenticationState())
    val uiState get() = _uiState.asStateFlow()

    // Actions
    fun handleEvent(authenticationEvent: AuthenticationEvent) {
        when (authenticationEvent) {
            AuthenticationEvent.ToggleAuthenticationMode -> toggleAuthenticationMode()
            is AuthenticationEvent.EmailChanged -> updateEmail(authenticationEvent.emailAddress)
            is AuthenticationEvent.PasswordChanged -> updatePassword(authenticationEvent.password)
            AuthenticationEvent.Authentication -> authenticate()
            AuthenticationEvent.ErrorDismissed -> dismissError()
        }
    }

    // Helper functions
    private fun toggleAuthenticationMode() {
        val authenticationMode = _uiState.value.authenticationMode
        val newAuthenticationMode = if (
            authenticationMode == AuthenticationMode.SIGN_IN
        ) {
            AuthenticationMode.SIGN_UP
        } else {
            AuthenticationMode.SIGN_IN
        }
        _uiState.update {
            it.copy(authenticationMode = newAuthenticationMode)
        }
    }

    private fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    /** update password and enforce password validity. */
    private fun updatePassword(password: String) {

        val requirements = mutableListOf<PasswordRequirements>()
        // password length greater then 7
        if (password.length > 7) {
            requirements.add(PasswordRequirements.EIGHT_CHARACTERS)
        }
        // password contains upper case character
        if (password.any { it.isUpperCase() }) {
            requirements.add(PasswordRequirements.CAPITAL_LETTER)
        }
        // password contains any digit
        if (password.any { it.isDigit() }) {
            requirements.add(PasswordRequirements.NUMBER)
        }

        _uiState.update {
            it.copy(
                password = password,
                passwordRequirements = requirements.toList()
            )
        }
    }

    private fun authenticate() {
        _uiState.update { it.copy(isLoading = true) }

        // trigger network request here
        // Simulating a real scenario to ensure that the asynchronous work and live data
        // emissions happen using the expected dispatchers.
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)

            // Simulate an error
            withContext(Dispatchers.Main) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Something went wrong!"
                    )
                }
            }
        }
    }

    private fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }
}