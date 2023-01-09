package com.nims.settings

import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.nims.R
import org.junit.Rule
import org.junit.Test

class SettingsTest {

    // When using this rule, we don’t need to specify any form of activity for our composables to be launched in,
    // the test rule will handle that for us.
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun enable_Notifications_Setting_Is_Displayed() {
        assertSettingIsDisplayed(R.string.setting_enable_notifications)

    }

    @Test
    fun show_Hints_Setting_Is_Displayed() {
        assertSettingIsDisplayed(
            R.string.setting_show_hints)
    }

    @Test
    fun view_Subscription_Setting_Is_Displayed() {
        assertSettingIsDisplayed(
            R.string.setting_manage_subscription)
    }

    @Test
    fun marketing_Options_Setting_Is_Displayed() {
        assertSettingIsDisplayed(
            R.string.setting_option_marketing)
    }

    @Test
    fun theme_Setting_Is_Displayed() {
        assertSettingIsDisplayed(
            R.string.setting_option_theme)
    }

    @Test
    fun app_Version_Setting_Is_Displayed() {
        assertSettingIsDisplayed(
            R.string.setting_app_version_title)
    }

    private fun assertSettingIsDisplayed(
        @StringRes title: Int
    ) {
        composeTestRule.setContent {
            SettingsScreen {}
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(title)
        ).assertIsDisplayed()
    }
}


