package com.nims.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nims.R
import com.nims.settings.model.MarketingOption
import com.nims.settings.model.SettingsState
import com.nims.settings.model.Theme
import com.nims.settings.ui.*
import com.nims.ui.theme.MaterialSettingsTheme

/** Entry point to Settings page, no arguments supplied. */
@Composable
fun SettingsScreen(
    popBackstack: () -> Unit
) {
    val viewModel: SettingsViewModel = viewModel()
    // Wrapping your composable hierarchy in a theme allows all components to be consistently styled.
    MaterialSettingsTheme {
        // single source of truth from the VM.
        val state = viewModel.uiState.collectAsState().value
        SettingsList(
            state = state,
            onToggleNotificationSetting = viewModel::toggleNotificationSettings,
            onShowHintsToggled = viewModel::toggleHintSettings,
            setMarketingOption = viewModel::setMarketingSettings,
            setTheme = viewModel::setTheme,
            popBackstack = popBackstack,
            appVersion = stringResource(id = R.string.setting_app_version)
        )
    }
}

/**
 * - A parent column to vertically display all of the child components
 * - A Top App Bar to represent the app bar of the screen
 * - Individual Row composables that represent each of the setting items
 * - A collection of Divider and Spacer composables to create some separation between setting items
 *
 * @param modifier - it’s good practice to allow a modifier to be passed into a composable,
 * this means that the parent who is composing the child can control the composition to some extent.
 * This also helps to keep your composable functions re-usable across your UI.
 * @param state
 */
@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState,
    onToggleNotificationSetting: () -> Unit,
    onShowHintsToggled: () -> Unit,
    setMarketingOption: (option: MarketingOption) -> Unit,
    setTheme: (theme: Theme) -> Unit,
    popBackstack: () -> Unit,
    appVersion: String,
) {
    // When passing in a Modifier via the composable function,
    // this should always be applied at the highest point within our composable - the parent
    Column(
        modifier = modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        TopAppBar(popBackstack = popBackstack)
        NotificationSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_enable_notifications),
            checked = state.notificationsEnabled,
            onToggleNotificationSettings = onToggleNotificationSetting
        )
        Divider()
        HintSettingsItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_show_hints),
            checked = state.hintsEnabled,
            onShowHintsToggled = onShowHintsToggled
        )
        Divider()
        ManageSubscriptionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.setting_manage_subscription),
            onSettingClicked = {
                // handle setting clicked
            }
        )
        Divider()
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = state.marketingOption,
            onOptionSelected = setMarketingOption
        )
        Divider()
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = state.themeOption,
            onOptionSelected = setTheme
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        AppVersionSettingItem(modifier = Modifier.fillMaxWidth(), appVersion = appVersion)
        Divider()
    }
}

@Preview
@Composable
fun Preview_SettingsList() {
    MaterialSettingsTheme {
        SettingsList(
            modifier = Modifier.fillMaxSize(),
            state = SettingsState(),
            onToggleNotificationSetting = {},
            onShowHintsToggled = {},
            setMarketingOption = {},
            setTheme = {},
            popBackstack = {},
            appVersion = "1.0.0"
        )
    }
}