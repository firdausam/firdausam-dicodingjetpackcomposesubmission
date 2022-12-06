package com.firdausam.dicodingjcomposesub.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.data.fakeAnime
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimeItemTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                with(fakeAnime) {
                    AnimeItem(
                        title = title,
                        image = image,
                        type = type,
                        episodeCount = episodes,
                        memberCount = members,
                        score = score
                    )
                }
            }
        }
    }

    @Test
    fun animeContent_isDisplayed() {
        composeTestRule.onNodeWithText(fakeAnime.title).assertIsDisplayed()
        composeTestRule.onNodeWithText("${fakeAnime.type} (${fakeAnime.episodes})")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.members_count,
                fakeAnime.members
            )
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeAnime.score.toString()).assertIsDisplayed()
    }
}