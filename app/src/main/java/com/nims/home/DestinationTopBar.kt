package com.nims.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nims.home.model.Destination

@Composable
fun DestinationTopToolbar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigateUp: () -> Unit,
    onOpenDrawer: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    if (currentDestination.isRootDestination) {
        RootDestinationTopAppBar(
            modifier = modifier,
            currentDestination = currentDestination,
            openDrawer = { onOpenDrawer() },
            showSnackbar = { message -> showSnackbar(message) }
        )
    } else {
        ChildDestinationTopAppBar(
            modifier = modifier,
            title = currentDestination.title,
            onNavigateUp = { onNavigateUp() })
    }
}