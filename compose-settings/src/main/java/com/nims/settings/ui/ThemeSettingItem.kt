package com.nims.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.nims.settings.R
import com.nims.settings.model.Theme
import com.nims.settings.ui.theme.MaterialSettingsTheme

@Composable
fun ThemeSettingItem(
    modifier: Modifier = Modifier,
    selectedTheme: Theme,
    onOptionSelected: (option: Theme) -> Unit
) {
    // This is a good example of state that can be kept local to a composable function.
    // The rest of our screen does not need to know about this state,
    // so containing it locally keeps its responsibility clearly defined.
    // by remember - state will be persisted across recompositions.
    var expanded by remember { mutableStateOf(false) }

    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                // The ordering of modifiers matters!
                // We place the clickable modifier before the padding here for a reason.
                // If the padding was applied before, then the touch indicator for the click event
                // would not expand to fill the padded area.
                .clickable(
                    onClick = { expanded = !expanded },
                    onClickLabel = stringResource(id = R.string.cd_select_theme)
                )
                .padding(16.dp)

        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.setting_option_theme)
            )
            Text(
                text = stringResource(id = selectedTheme.label)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(16.dp, 0.dp),
        ) {
            Theme.values().forEach { theme ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(theme)
                        expanded = false
                    }
                ) {
                    Text(
                        text = stringResource(id = theme.label)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_ThemeSettingItem() {
    MaterialSettingsTheme {
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = Theme.LIGHT,
            onOptionSelected = {}
        )
    }
}

