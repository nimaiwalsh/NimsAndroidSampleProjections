package com.nims.authenticationform

import androidx.lifecycle.ViewModel
import com.nims.authenticationform.model.AuthenticationState
import kotlinx.coroutines.flow.MutableStateFlow

class AuthenticationViewModel : ViewModel() {

    val uiState = MutableStateFlow(AuthenticationState())
}