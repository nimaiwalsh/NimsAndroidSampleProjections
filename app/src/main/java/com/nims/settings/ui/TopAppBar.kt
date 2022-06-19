package com.nims.settings.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nims.R

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    popBackstack: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.title_settings)) },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(onClick = { popBackstack() }) {
                Icon(
                    tint = MaterialTheme.colors.onSurface,
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_go_back)
                )
            }
        }
    )
}