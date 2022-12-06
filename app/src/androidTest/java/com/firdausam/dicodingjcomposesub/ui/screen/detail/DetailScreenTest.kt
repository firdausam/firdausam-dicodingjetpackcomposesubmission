package com.firdausam.dicodingjcomposesub.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firdausam.dicodingjcomposesub.R
import com.firdausam.dicodingjcomposesub.data.FakeAnimeWithDataRepository
import com.firdausam.dicodingjcomposesub.data.fakeDetailAnime
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.*
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun detail_data_valid_with_favorite() {
        setContent(fakeDetailAnime.id)
        composeTestRule.onNodeWithText(fakeDetailAnime.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeDetailAnime.type).assertIsDisplayed()
        composeTestRule.onNodeWithText("#${fakeDetailAnime.ranked}").assertIsDisplayed()
        composeTestRule.onNodeWithText("#${fakeDetailAnime.popularity}").assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeDetailAnime.members.toString()).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeDetailAnime.synopsis).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_inactive).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_inactive).performClick()
        composeTestRule.waitUntil(3_000) {
            return@waitUntil composeTestRule.onAllNodesWithContentDescription(composeTestRule.getString(R.string.favorite_active))
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_active).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_active).performClick()
        composeTestRule.waitUntil(3_000) {
            return@waitUntil composeTestRule.onAllNodesWithContentDescription(composeTestRule.getString(R.string.favorite_inactive))
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_inactive).assertIsDisplayed()
    }

    @Test
    fun detail_data_invalid() {
        setContent(Int.MIN_VALUE)
        composeTestRule.onNodeWithTag(TagError).assertIsDisplayed()
    }

    private fun setContent(id: Int) {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                DetailScreen(
                    id = id,
                    onBackClick = { },
                    onToFavorite = { },
                    mySnackbar = rememberMySnackbar(),
                    viewModel = viewModel(
                        factory = ViewModelFactory(FakeAnimeWithDataRepository()),
                        extras = MutableCreationExtras().apply { set(ViewModelFactory.KeyId, id) }
                    )
                )
            }
        }
    }
}