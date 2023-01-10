package com.nims.inbox

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import com.nims.Tags
import com.nims.inbox.ui.EmailList
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class EmailListTest {

    // When using this rule, we don’t need to specify any form of activity
    // for our composables to be launched in, the test rule will handle that for us.
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Email_Items_Displayed() {
        // given
        val emails = EmailFactory.makeEmailList()
        composeTestRule.setContent {
            EmailList(
                emails = emails,
                onDeleteEmail = {}
            )
        }

        // then
        emails.forEachIndexed { index, email ->
            composeTestRule.onNodeWithTag(Tags.EmailInbox.TAG_EMAILS)
                .onChildAt(index)
                .performScrollTo()
                .assert(hasTestTag(Tags.EmailInbox.TAG_EMAIL + email.id))
        }
    }

    @Test
    fun Delete_Email_Triggered() {
        // given
        val emails = EmailFactory.makeEmailList()
        val deleteEmail: (id: String) -> Unit = mock()
        val indexToDelete = 2

        composeTestRule.setContent {
            EmailList(
                emails = emails,
                onDeleteEmail = deleteEmail,
            )
        }

        // when
        composeTestRule.onNodeWithTag(Tags.EmailInbox.TAG_EMAILS)
            .onChildAt(indexToDelete)
            .performTouchInput {
                swipeRight()
            }

        // then wait for UI to be in idle state - wait for dismiss animation to complete
        composeTestRule.waitForIdle()
        verify(deleteEmail).invoke(emails[indexToDelete].id)
    }
}