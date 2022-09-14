package com.nims.authenticationform.ui

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nims.R

@Composable
fun EmailInput(modifier: Modifier, email: String?, onEmailChanged: (email: String) -> Unit) {
    TextField(
        modifier = modifier,
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
        }
    )
}