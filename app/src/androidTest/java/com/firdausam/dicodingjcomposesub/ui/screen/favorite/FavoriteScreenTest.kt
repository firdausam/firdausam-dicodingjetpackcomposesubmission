package com.firdausam.dicodingjcomposesub.ui.screen.favorite

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.viewmodel.compose.viewModel
import com.firdausam.dicodingjcomposesub.data.FakeAnimeWithDataRepository
import com.firdausam.dicodingjcomposesub.data.FakeAnimeWithoutDataRepository
import com.firdausam.dicodingjcomposesub.ui.ViewModelFactory
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.TagEmpty
import com.firdausam.dicodingjcomposesub.util.TagListItem
import org.junit.Rule
import org.junit.Test

class FavoriteScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun favorite_show_data_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                FavoriteScreen(
                    navigateToDetail = {}, viewModel = viewModel(
                        factory = ViewModelFactory(FakeAnimeWithDataRepository())
                    )
                )
            }
        }
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().assertIsDisplayed()
    }

    @Test
    fun favorite_empty_data_test() {
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                FavoriteScreen(
                    navigateToDetail = {}, viewModel = viewModel(
                        factory = ViewModelFactory(FakeAnimeWithoutDataRepository())
                    )
                )
            }
        }
        composeTestRule.onNodeWithTag(TagEmpty).assertIsDisplayed()
    }
}