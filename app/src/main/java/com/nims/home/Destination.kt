package com.nims.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

/** Destinations withing the Home of our app. */
sealed class Destination(
    val path: String,
    val title: String,
    val icon: ImageVector? = null
) {
    object Home : Destination(ROUTE_HOME, "Home")

    object Feed : Destination(ROUTE_FEED, "Feed", Icons.Default.List)

    object Contacts : Destination(ROUTE_CONTACTS, "Contacts", Icons.Default.Person)

    object Calendar : Destination(ROUTE_CALENDAR, "Calendar", Icons.Default.DateRange)

    object Settings : Destination(ROUTE_SETTINGS, "Settings", Icons.Default.Settings)

    object Upgrade : Destination(ROUTE_UPGRADE, "Upgrade", Icons.Default.Star)

    companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_FEED = "feed"
        const val ROUTE_CONTACTS = "contacts"
        const val ROUTE_CALENDAR = "calendar"
        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_UPGRADE = "upgrade"

        fun fromString(route: String): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                Settings.path -> Settings
                Upgrade.path -> Upgrade
                else -> Home
            }
        }
    }
}