package com.nims.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nims.home.model.Destination
import com.nims.home.model.NavigationBarItem.Companion.buildNavigationBarItems

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier = modifier) {
        buildNavigationBarItems(
            currentDestination,
            onNavigate
        ).forEach {
            BottomNavigationItem(
                selected = it.selected,
                onClick = it.onClick,
                icon = it.icon,
                label = it.label
            )
        }
    }
}



