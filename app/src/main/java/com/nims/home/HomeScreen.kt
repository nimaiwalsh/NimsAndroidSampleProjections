package com.nims.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nims.R
import com.nims.home.model.Destination
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    val navBackStackEntry = navController.currentBackStackEntryAsState()

    /**
     * derivedStateOf - Allows us to create a state object based on the calculation of our Destination.
     * The internals of derivedStateOf allows our result to be cached,
     * meaning that the value will not be recalculated across compositions unless the
     * value of the navBackStackEntry reference changes.
     * */
    val currentDestination by derivedStateOf {
        navBackStackEntry.value?.destination?.route?.let {
            Destination.fromString(it)
        } ?: run {
            Destination.Home
        }
    }

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            DestinationTopToolbar(
                modifier = Modifier.fillMaxWidth(),
                currentDestination = currentDestination,
                onNavigateUp = { navController.popBackStack() },
                onOpenDrawer = { coroutineScope.launch { drawerState.open() } },
                showSnackbar = { message ->
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(message)
                    }
                }
            )
        },
        bottomBar = {
            if (currentDestination.isRootDestination) {
                BottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = {
                        navController.navigate(it.path) {
                            // clear all backstack entries as bottom nav is a root destination
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // This means that each destination can only have one back stack entry,
                            // this avoids duplicate copies existing in our back stack should a
                            // destination be reselected.
                            launchSingleTop = true
                            // Whether this navigation action should restore any state previously
                            // saved by PopUpToBuilder. This means if a previously selected item is
                            // reselected, its state will be restored.
                            restoreState = true
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (currentDestination == Destination.Feed) {
                FloatingActionButton(onClick = { navController.navigate(Destination.Creation.path) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.cd_create_item)
                    )
                }
            }
        },
        drawerContent = {
            DrawerContent(
                onNavigationSelected = { destination ->
                    navController.navigate(destination.path)
                    coroutineScope.launch { drawerState.close() }
                },
                onLogout = {})
        }
    ) { padding ->
        // Main content of your screen goes here
        Navigation(
            modifier = modifier.padding(padding),
            navController = navController
        )
    }
}