package com.nims.inbox

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.test.platform.app.InstrumentationRegistry
import com.nims.R
import com.nims.Tags
import com.nims.inbox.model.InboxState
import com.nims.inbox.model.InboxStatus
import com.nims.inbox.ui.EmailInbox
import org.junit.Rule
import org.junit.Test


class EmailInboxTest {

    // When using this rule, we don’t need to specify any form of activity
    // for our composables to be launched in, the test rule will handle that for us.
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Inbox_Title_Displayed() {
        val inboxState = InboxState(emails = EmailFactory.makeEmailList())

        composeTestRule.setContent {
            EmailInbox(
                inboxState = inboxState,
                handleEvent = {},
            )
        }

        composeTestRule
            .onNodeWithText(
                text = InstrumentationRegistry.getInstrumentation().targetContext.getString(
                    R.string.title_inbox,
                    inboxState.emails?.count()
                )
            )
            .assertIsDisplayed()
    }

    @Test
    fun Loading_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(
                    inboxState = InboxState(status = InboxStatus.LOADING),
                    handleEvent = {},
                )
            }

            onNodeWithTag(Tags.EmailInbox.TAG_LOADING).assertIsDisplayed()
            onNodeWithTag(Tags.EmailInbox.TAG_EMAILS).assertDoesNotExist()
            onNodeWithTag(Tags.EmailInbox.TAG_ERROR).assertDoesNotExist()
            onNodeWithTag(Tags.EmailInbox.TAG_EMPTY).assertDoesNotExist()
        }
    }

    @Test
    fun Error_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(
                    inboxState = InboxState(status = InboxStatus.ERROR),
                    handleEvent = {},
                )
            }

            onNodeWithTag(Tags.EmailInbox.TAG_LOADING).assertDoesNotExist()
            onNodeWithTag(Tags.EmailInbox.TAG_EMAILS).assertDoesNotExist()
            onNodeWithTag(Tags.EmailInbox.TAG_ERROR).assertIsDisplayed()
            onNodeWithTag(Tags.EmailInbox.TAG_EMPTY).assertDoesNotExist()
        }
    }

    @Test
    fun Content_State_Displayed() {
        composeTestRule.run {
            setContent {
                EmailInbox(
                    inboxState = InboxState(status = InboxStatus.SUCCESS),
                    handleEvent = {},
                )
            }

            onNodeWithTag(Tags.EmailInbox.TAG_LOADING).assertDoesNotExist()
            onNodeWithTag(Tags.EmailInbox.TAG_EMAILS).assertIsDisplayed()
            onNodeWithTag(Tags.EmailInbox.TAG_ERROR).assertDoesNotExist()
            onNodeWithTag(Tags.EmailInbox.TAG_EMPTY).assertDoesNotExist()
        }
    }

    @Test
    fun Item_Dismissed_When_Swiped() {
        // given
        composeTestRule.setContent {
            InboxScreen()
        }

        // when dismiss first email item
        composeTestRule.onNodeWithTag(Tags.EmailInbox.TAG_EMAILS)
            .onChildAt(0)
            .performTouchInput {
                swipeRight()
            }

        // then - email list 1 less than original list
        val emails = EmailFactory.makeEmailList()
        composeTestRule.onNodeWithTag(Tags.EmailInbox.TAG_EMAILS)
            .onChildren()
            .assertCountEquals(emails.count() - 1)
    }

    @Test
    fun Remaining_Items_Displayed_When_Another_Dismissed() {
        // given
        composeTestRule.setContent {
            InboxScreen()
        }

        // when first email item deleted
        composeTestRule.onNodeWithTag(Tags.EmailInbox.TAG_EMAILS)
            .onChildAt(0)
            .performTouchInput {
                swipeRight()
            }

        // then list contains remaining email items
        val emails = EmailFactory.makeEmailList()
        emails.takeLast(emails.count() - 1)
            .forEachIndexed { index, email ->
                composeTestRule.onNodeWithTag(Tags.EmailInbox.TAG_EMAILS)
                    .onChildAt(index)
                    .performScrollTo()
                    .assert(hasTestTag(Tags.EmailInbox.TAG_EMAIL + email.id))
            }

    }
}