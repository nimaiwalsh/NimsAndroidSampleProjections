package com.nims.authenticationform.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.nims.R

@Composable
fun PasswordInput(
    modifier: Modifier,
    password: String?,
    onPasswordChanged: (password: String) -> Unit,
    passwordFocusRequester: FocusRequester,
    onDoneClicked: () -> Unit,
) {

    var isPasswordHidden by remember { mutableStateOf(true) }

    val passwordOnClickLabel = if (isPasswordHidden) {
        stringResource(id = R.string.cd_show_password)
    } else {
        stringResource(id = R.string.cd_hide_password)
    }

    TextField(
        modifier = modifier.focusRequester(passwordFocusRequester),
        value = password ?: "",
        onValueChange = { onPasswordChanged(it) },
        label = {
            Text(
                text = stringResource(id = R.string.label_password)
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription = null)
        },
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable(
                    onClickLabel = passwordOnClickLabel
                ) {
                    isPasswordHidden = !isPasswordHidden
                },
                imageVector = if (isPasswordHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = null,
            )
        },
        visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDoneClicked()
            }
        )
    )
}