package com.nims.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nims.R
import com.nims.ui.theme.MaterialSettingsTheme

@Composable
fun ManageSubscriptionSettingItem(
    modifier: Modifier = Modifier,
    title: String,
    onSettingClicked: () -> Unit
) {
    SettingItem(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                // Here we place the clickable modifier before the padding modifier.
                // This is because the ordering of modifiers matters,
                // and this ensures that the whole padded area of the composable is clickable.
                .clickable(
                    onClickLabel = stringResource(R.string.cd_open_subscription)
                ) { onSettingClicked() }
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun Preview_ManageSubscriptionSettingItems() {
    MaterialSettingsTheme {
        ManageSubscriptionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = "Manage subscription",
            onSettingClicked = {}
        )
    }
}