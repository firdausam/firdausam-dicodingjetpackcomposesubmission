package com.firdausam.dicodingjcomposesub.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.firdausam.dicodingjcomposesub.data.fakeAnimeList
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.TagListItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimeContentTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                AnimeContent(fakeAnimeList) {

                }
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun animeContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeAnimeList[0].title).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TagListItem).performScrollToIndex(10)
        composeTestRule.onNodeWithText(fakeAnimeList[9].title).assertIsDisplayed()
    }
}