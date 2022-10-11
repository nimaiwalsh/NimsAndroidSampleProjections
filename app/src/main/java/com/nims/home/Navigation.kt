package com.nims.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nims.home.model.Destination

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Home.path
    ) {
        // Nested NavGraph: Root Destinations used for Bottom Navigation
        navigation(
            // start destination of the nested nav graph
            startDestination = Destination.Feed.path,
            // route to this nested nav graph
            route = Destination.Home.path
        ) {
            composable(route = Destination.Feed.path) {
                ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Feed
                )
            }
            composable(route = Destination.Contacts.path) {
                ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Contacts
                )
            }
            composable(route = Destination.Calendar.path) {
                ContentArea(
                    modifier = Modifier.fillMaxSize(),
                    destination = Destination.Calendar
                )
            }
        }

        // Nested NavGraph: Features
        navigation(
            startDestination = Destination.Add.path,
            route = Destination.Creation.path
        ) {
            composable(route = Destination.Add.path) {
                ContentArea(modifier = Modifier.fillMaxSize(), Destination.Add)
            }
        }

        // Top level navigation
        composable(route = Destination.Upgrade.path) {
            ContentArea(
                modifier = Modifier.fillMaxSize(),
                Destination.Upgrade
            )
        }

        composable(route = Destination.Settings.path) {
            ContentArea(
                modifier = Modifier.fillMaxSize(),
                Destination.Settings
            )
        }
    }
}