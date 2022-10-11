package com.nims.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nims.R
import com.nims.home.model.Destination
import java.util.*

@Composable
fun ColumnScope.DrawerContent(
    onNavigationSelected: (Destination) -> Unit,
    onLogout: () -> Unit
) {
    // Header content
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.home_label_name),
        fontSize = 20.sp
    )
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.home_label_email),
        fontSize = 16.sp
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )

    // Navigation items
    DrawItem(modifier = Modifier.fillMaxWidth(), label = Destination.Upgrade.path) {
        onNavigationSelected(Destination.Upgrade)
    }
    Spacer(modifier = Modifier.height(8.dp))
    DrawItem(modifier = Modifier.fillMaxWidth(), label = Destination.Settings.path) {
        onNavigationSelected(Destination.Settings)
    }
    Spacer(modifier = Modifier.weight(1f))
    DrawItem(modifier = Modifier.fillMaxWidth(), label = stringResource(R.string.logout)) {
        onLogout()
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun DrawItem(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier
            .clickable { onClick() }
            .padding(16.dp),
        text = label.replaceFirstChar { it.titlecase(Locale.getDefault()) }
    )
}