package com.nims.settings.ui

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// When creating a reusable composables function,
// allowing composable content to be provided into the function allows us to create flexible
// composables that can be utilised for different use cases
@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            // Using minimum size modifiers instead of static sizing modifiers means that the
            // composables can grow when they need to.
            // This makes your UI more responsive across different screen sizes,
            // and also helps to keep your UI accessible for users
            // who might be adjusting text sizes via their system settings.
            .heightIn(56.dp)
    ) {
        content()
    }
}