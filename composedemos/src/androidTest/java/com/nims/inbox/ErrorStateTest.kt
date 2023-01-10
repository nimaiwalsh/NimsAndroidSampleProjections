package com.nims.inbox

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.nims.R
import com.nims.inbox.ui.EmptyState
import com.nims.inbox.ui.ErrorState
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ErrorStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Refresh_Triggered() {
        val onRefresh: () -> Unit = mock()

        // given
        composeTestRule.setContent {
            ErrorState(onTryAgain = onRefresh)
        }

        // when
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_try_again)
        ).performClick()

        // then
        verify(onRefresh).invoke()
    }
}