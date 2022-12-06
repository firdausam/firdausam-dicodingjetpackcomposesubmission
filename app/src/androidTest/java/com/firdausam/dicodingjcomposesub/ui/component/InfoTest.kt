package com.firdausam.dicodingjcomposesub.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.data.fakeInfoItem
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.getString
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InfoTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var title: String
    private lateinit var value: String

    @Before
    fun setUp() {
        title = composeTestRule.getString(fakeInfoItem.title)
        value = fakeInfoItem.value
    }

    @Test
    fun info_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                Info(title = title, value = value)
            }
        }
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.info_title,
                title
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeInfoItem.value).assertIsDisplayed()
    }

    @Test
    fun description_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                Description(title = title, value = value)
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(value).assertIsDisplayed()
    }

    @Test
    fun title_divider_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                TitleDivider(title = value)
            }
        }
        composeTestRule.onNodeWithText(value).assertIsDisplayed()
    }

    @Test
    fun statistic_card_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                StatisticCard(title = title, value = value)
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(value).assertIsDisplayed()
    }
}