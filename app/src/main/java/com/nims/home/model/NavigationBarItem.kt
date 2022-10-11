package com.nims.home.model

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class NavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit,
) {
    companion object {
        fun buildNavigationBarItems(
            currentDestination: Destination,
            onNavigate: (destination: Destination) -> Unit
        ): List<NavigationBarItem> {
            return listOf(
                Destination.Feed,
                Destination.Contacts,
                Destination.Calendar
            ).map {
                NavigationBarItem(
                    selected = currentDestination.path == it.path,
                    onClick = { onNavigate(it) },
                    icon = {
                        it.icon?.let { image ->
                            Icon(imageVector = image, contentDescription = it.path)
                        }
                    },
                    label = { Text(text = it.title) }
                )
            }
        }
    }
}