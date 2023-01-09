package com.nims.authenticationform.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nims.R

@Composable
fun Requirement(
    modifier: Modifier = Modifier,
    message: String,
    satisfied: Boolean,
) {
    val requirementStatus = if (satisfied) {
        stringResource(id = R.string.password_requirement_satisfied, message)
    } else {
        stringResource(id = R.string.password_requirement_not_satisfied, message)
    }

    val tint = if (satisfied) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface.copy(alpha = 0.4f)

    /**
     * Add accessibility semantics to the row and ignore all children i.e text/icon, also clear the semantics from
     * the Text as the parent is now handling accessibility
     */
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {
            text = AnnotatedString(requirementStatus)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(modifier = Modifier.size(12.dp), imageVector = Icons.Default.Check, contentDescription = null, tint = tint)
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.clearAndSetSemantics { }, text = message, fontSize = 12.sp, color = tint)
    }
}