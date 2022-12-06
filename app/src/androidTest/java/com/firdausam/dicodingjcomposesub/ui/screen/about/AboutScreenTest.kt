package com.firdausam.dicodingjcomposesub.ui.screen.about

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.onNodeContentDescWithStringId
import com.firdausam.dicodingjcomposesub.util.onNodeWithStringId
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                AboutScreen {}
            }
        }
    }

    @Test
    fun about_data_test() {
        composeTestRule.onNodeWithStringId(R.string.my_name).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.my_email_dicoding).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.profile).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.dicoding).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.linkedin).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.github).assertIsDisplayed()
    }
}