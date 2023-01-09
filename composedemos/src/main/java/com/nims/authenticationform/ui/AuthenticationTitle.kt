package com.nims.authenticationform.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nims.R
import com.nims.authenticationform.model.AuthenticationMode

@Composable
fun AuthenticationTitle(modifier: Modifier = Modifier, authenticationMode: AuthenticationMode) {

    val title = if (authenticationMode == AuthenticationMode.SIGN_IN) {
        R.string.label_sign_in_to_account
    } else {
        R.string.label_sign_up_for_account
    }

    Text(text = stringResource(title), fontSize = 24.sp, fontWeight = FontWeight.Black)
}