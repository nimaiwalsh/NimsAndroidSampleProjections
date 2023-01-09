package com.nims.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nims.R
import com.nims.home.model.Destination

@Composable
fun RootDestinationTopAppBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    openDrawer: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = currentDestination.title)
        },
        navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.cd_open_menu)
                )
            }
        },
        actions = {
            if (currentDestination !== Destination.Feed) {
                val snackbarMessage = stringResource(id = R.string.not_available_yet)
                IconButton(onClick = { showSnackbar(snackbarMessage) }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.cd_more_information)
                    )
                }
            }
        }
    )
}