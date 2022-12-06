package com.firdausam.dicodingjcomposesub.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firdausam.dicodingjcomposesub.data.FakeAnimeWithDataRepository
import com.firdausam.dicodingjcomposesub.data.fakeErrorMessage
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.firdausam.dicodingjcomposesub.R

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                HomeScreen(
                    navigateToDetail = {}, viewModel = viewModel(
                        factory = ViewModelFactory(FakeAnimeWithDataRepository())
                    )
                )
            }
        }
    }

    @Test
    fun home_show_data_test() {
        composeTestRule.onNodeWithTag(SearchField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SearchField).performTextInput("")
        composeTestRule.waitUntil(3_000) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagListItem)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().assertIsDisplayed()
    }

    @Test
    fun home_empty_data_test() {
        composeTestRule.onNodeWithTag(SearchField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SearchField).performTextInput(TagEmpty)
        composeTestRule.onNodeWithText(TagEmpty).assertIsDisplayed()
        composeTestRule.waitUntil(3_000) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagEmpty)
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule.onNodeWithTag(TagEmpty).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.empty_message).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }

    @Test
    fun home_error_data_test() {
        composeTestRule.onNodeWithTag(SearchField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SearchField).performTextInput(TagError)
        composeTestRule.onNodeWithText(TagError).assertIsDisplayed()
        composeTestRule.waitUntil(3_000) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagError)
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule.onNodeWithTag(TagError).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeErrorMessage).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.message).assertIsDisplayed()
    }
}