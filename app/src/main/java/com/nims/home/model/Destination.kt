package com.nims.home.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

/** Destinations withing the Home of our app. */
sealed class Destination(
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = true
) {

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_FEED = "feed"
        const val ROUTE_CONTACTS = "contacts"
        const val ROUTE_CALENDAR = "calendar"
        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_UPGRADE = "upgrade"
        const val ROUTE_CREATION = "creation"
        const val ROUTE_ADD = "add"

        fun fromString(route: String): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                Settings.path -> Settings
                Upgrade.path -> Upgrade
                Creation.path -> Creation
                Add.path ->  Add
                else -> Home
            }
        }
    }

    object Home : Destination(ROUTE_HOME)

    object Feed : Destination(ROUTE_FEED, Icons.Default.List)

    object Contacts : Destination(ROUTE_CONTACTS, Icons.Default.Person)

    object Calendar : Destination(ROUTE_CALENDAR, Icons.Default.DateRange)

    object Settings : Destination(ROUTE_SETTINGS, Icons.Default.Settings, isRootDestination = false)

    object Upgrade : Destination(ROUTE_UPGRADE, Icons.Default.Star, isRootDestination = false)

    object Creation : Destination(ROUTE_CREATION, isRootDestination = false)

    object Add : Destination(ROUTE_ADD, Icons.Default.Add, isRootDestination = false)

    val title = path.replaceFirstChar { it.uppercase() }
}