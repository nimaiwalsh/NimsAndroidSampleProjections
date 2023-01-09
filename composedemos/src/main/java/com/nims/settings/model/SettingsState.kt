package com.nims.settings.model

/** State reference for Settings UI
 * Acts as single source of truth for the UI.
 */
data class SettingsState(
    val notificationsEnabled: Boolean = false,
    val hintsEnabled: Boolean = false,
    val marketingOption: MarketingOption = MarketingOption.ALLOWED,
    val themeOption: Theme = Theme.SYSTEM
)