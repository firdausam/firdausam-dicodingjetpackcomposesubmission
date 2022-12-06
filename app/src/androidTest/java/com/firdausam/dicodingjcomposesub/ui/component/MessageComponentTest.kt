package com.firdausam.dicodingjcomposesub.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.data.InformationTest
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.onNodeContentDescWithStringId
import com.firdausam.dicodingjcomposesub.util.onNodeWithStringId
import org.junit.Rule
import org.junit.Test

class MessageComponentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun message_common_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                MessageCommon(
                    message = InformationTest,
                    imageVector = Icons.Filled.Info
                )
            }
        }

        composeTestRule.onNodeWithText(InformationTest).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }

    @Test
    fun empty_common_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                EmptyCommon()
            }
        }

        composeTestRule.onNodeWithStringId(R.string.empty_message).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }

    @Test
    fun empty_common_custom_message_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                EmptyCommon(message = InformationTest)
            }
        }

        composeTestRule.onNodeWithText(InformationTest).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }

    @Test
    fun error_common_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                ErrorCommon()
            }
        }

        composeTestRule.onNodeWithStringId(R.string.error_message).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }

    @Test
    fun error_common_custom_message_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                ErrorCommon(errorMessage = InformationTest)
            }
        }

        composeTestRule.onNodeWithText(InformationTest).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }
}