package com.nims.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
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
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
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
            val snackbarMessage = stringResource(id = R.string.not_available_yet)

            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                actions = {
                    if (currentDestination !== Destination.Feed) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(snackbarMessage)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = stringResource(id = R.string.cd_more_information)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        }
    ) { padding ->
        // Main content of your screen goes here
        Navigation(
            modifier = modifier.padding(padding),
            navController = navController
        )
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier = modifier) {
        listOf(
            Destination.Feed,
            Destination.Contacts,
            Destination.Calendar
        ).forEach {
            BottomNavigationItem(
                selected = currentDestination.path == it.path,
                icon = {
                    it.icon?.let { image ->
                        Icon(imageVector = image, contentDescription = it.path)
                    }
                },
                onClick = { onNavigate(it) },
                label = {
                    Text(text = it.title)
                }
            )
        }
    }
}