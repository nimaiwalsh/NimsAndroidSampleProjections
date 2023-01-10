package com.nims.authenticationform

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.nims.R
import com.nims.authenticationform.model.AuthenticationState
import com.nims.authenticationform.ui.AuthenticationContent
import com.nims.Tags.AuthenticationForm.TAG_AUTHENTICATE_BUTTON
import com.nims.Tags.AuthenticationForm.TAG_AUTHENTICATION_TOGGLE
import com.nims.Tags.AuthenticationForm.TAG_ERROR_ALERT
import com.nims.Tags.AuthenticationForm.TAG_INPUT_EMAIL
import com.nims.Tags.AuthenticationForm.TAG_INPUT_PASSWORD
import com.nims.Tags.AuthenticationForm.TAG_PROGRESS
import org.junit.Rule
import org.junit.Test

class AuthenticationFormTest {
    // When using this rule, we don’t need to specify any form of activity
    // for our composables to be launched in, the test rule will handle that for us.
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun sign_In_Title_Displayed_By_Default() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.label_sign_in_to_account)
        ).assertIsDisplayed()
    }

    @Test
    fun need_Account_Displayed_By_Default() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_need_account)
        ).assertIsDisplayed()
    }

    @Test
    fun sign_Up_Title_Displayed_After_Toggled() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_need_account)
        ).performClick()

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.label_sign_up_for_account)
        ).assertIsDisplayed()
    }

    @Test
    fun sign_Up_Button_Displayed_After_Toggle() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_TOGGLE).performClick()

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON).assertTextEquals(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.action_sign_up)
        )
    }

    @Test
    fun already_Have_Account_Displayed_After_Toggle() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_TOGGLE).apply {
            performClick()
            assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(R.string.action_already_have_account)
            )
        }
    }

    @Test
    fun authentication_Button_Disabled_By_Default() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun authentication_Button_Enabled_With_Valid_Content() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .performTextInput("contact@domain.com")

        composeTestRule
            .onNodeWithTag(TAG_INPUT_PASSWORD)
            .performTextInput("contact@domain.com")

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertIsEnabled()
    }

    @Test
    fun error_Alert_Not_Displayed_By_Default() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithTag(TAG_ERROR_ALERT)
            .assertDoesNotExist()
    }

    @Test
    fun error_Alert_Displayed_After_Error() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(error = "Some error"),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_ERROR_ALERT)
            .assertIsDisplayed()
    }

    @Test
    fun progress_Not_Displayed_By_Default() {
        composeTestRule.setContent {
            AuthenticationScreen()
        }

        composeTestRule.onNodeWithTag(TAG_PROGRESS).assertDoesNotExist()
    }

    @Test
    fun progress_Not_Displayed_While_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(isLoading = true),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_PROGRESS).assertIsDisplayed()
    }

    @Test
    fun progress_Not_Displayed_After_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(
                authenticationState = AuthenticationState(
                    email = "some@domain.com",
                    password = "password"
                ),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON).performClick()
        composeTestRule.onNodeWithTag(TAG_PROGRESS).assertDoesNotExist()
    }
}