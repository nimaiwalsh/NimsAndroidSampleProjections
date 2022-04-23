package com.nims.settings.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nims.settings.R

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    androidx.compose.material.TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        contentPadding = PaddingValues(start = 12.dp)
    ) {
        Icon(
            tint = MaterialTheme.colors.onSurface,
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.cd_go_back)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = R.string.title_settings),
            color = MaterialTheme.colors.onSurface,
            fontSize = 18.sp,
        )
    }
}