package com.nims.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nims.R
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun NotificationSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onToggleNotificationSettings: () -> Unit
) {
    val notificationsEnabledState = if (checked) {
        stringResource(id = R.string.cd_hints_disabled)
    } else stringResource(id = R.string.cd_hints_disabled)

    // “Using the Surface composable saves us from using a Box composable and applying
    // a collection of styling ourselves, when this component already exists to do it for us.”
    SettingItem(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = checked,
                    onValueChange = { onToggleNotificationSettings() },
                    role = Role.Switch
                )
                .semantics {
                    stateDescription = notificationsEnabledState
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = checked,
                onCheckedChange = null
            )
        }
    }
}

@Preview
@Composable
fun Preview_NotificationSetting() {
    MaterialSettingsTheme {
        NotificationSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = "Enable notifications",
            checked = true,
            onToggleNotificationSettings = { }
        )
    }
}