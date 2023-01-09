package com.nims.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nims.R

@Composable
fun ChildDestinationTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateUp: () -> Unit
) {
    TopAppBar(modifier = modifier, title = { Text(text = title) }, navigationIcon = {
        IconButton(onClick = { onNavigateUp() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_navigate_up)
            )
        }
    }
    )
}