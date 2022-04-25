package com.nims.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nims.settings.R
import com.nims.settings.model.MarketingOption
import com.nims.settings.ui.theme.MaterialSettingsTheme

@Composable
fun MarketingSettingItem(
    modifier: Modifier = Modifier,
    selectedOption: MarketingOption,
    onOptionSelected: (option: MarketingOption) -> Unit
) {
    val options = stringArrayResource(id = R.array.setting_options_marketing_choice)

    SettingItem(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = stringResource(id = R.string.setting_option_marketing))
            Spacer(modifier = Modifier.height(8.dp))
            options.forEachIndexed { index, option ->
                Row(
                    modifier = Modifier
                        // Outside of accessibility services, this improves the usability of the
                        // component for all users of our app - as the row can be clicked as a complete item,
                        // rather than relying on individual component interactions.
                        .selectable(
                            selected = selectedOption.id == index,
                            role = Role.RadioButton,
                            onClick = {
                                val marketingOption =
                                    if (index == MarketingOption.ALLOWED.id) MarketingOption.ALLOWED
                                    else MarketingOption.NOT_ALLOWED
                                onOptionSelected(marketingOption)
                            }
                        )
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    RadioButton(
                        // Using the index as the ID of our item isn’t the most scalable approach,
                        // but it helps to keep things simple for the sake of this example.
                        // If building this for production,
                        // it would be worth thinking about having the ID represented by something
                        // that didn’t rely on the index of a list.
                        selected = selectedOption.id == index,
                        // listener moved to row
                        onClick = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 18.dp),
                        text = option
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_MarketingSettingItem() {
    MaterialSettingsTheme {
        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = MarketingOption.ALLOWED,
            onOptionSelected = { }
        )
    }
}