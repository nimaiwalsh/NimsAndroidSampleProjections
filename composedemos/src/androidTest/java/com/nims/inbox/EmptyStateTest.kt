package com.nims.inbox

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.nims.inbox.ui.EmptyState
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import com.nims.R
import org.mockito.kotlin.verify

class EmptyStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Refresh_Triggered() {
        val onRefresh: () -> Unit = mock()

        // given
        composeTestRule.setContent {
            EmptyState(onRefresh = onRefresh)
        }

        // when
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_check_again)
        ).performClick()

        // then
        verify(onRefresh).invoke()
    }
}