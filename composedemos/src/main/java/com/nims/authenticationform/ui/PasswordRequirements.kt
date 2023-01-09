package com.nims.authenticationform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nims.authenticationform.model.PasswordRequirements

@Composable
fun PasswordRequirements(modifier: Modifier = Modifier, satisfiedRequirements: List<PasswordRequirements>) {

    Column(modifier = modifier) {
        PasswordRequirements.values().forEach { requirement ->
            Requirement(
                message = stringResource(id = requirement.label),
                satisfied = satisfiedRequirements.contains(requirement)
            )
        }
    }
}