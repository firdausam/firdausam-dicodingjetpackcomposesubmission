package com.firdausam.dicodingjcomposesub

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.firdausam.dicodingjcomposesub.data.local.enity.AnimeEntity
import com.firdausam.dicodingjcomposesub.data.local.room.AnimeDatabase
import com.firdausam.dicodingjcomposesub.ui.navigation.Screen
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.*
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimeAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.activity.let {
            it.lifecycleScope.launch {
                AnimeDatabase.getInstance(it.applicationContext).animeDao().deleteAllAnime()
            }
        }
        composeTestRule.setContent {
            DicodingJetpackComposeSubmissionTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                AnimeApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_bottomNavigation() {
        composeTestRule.onNodeContentDescWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeContentDescWithStringId(R.string.menu_about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeContentDescWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun show_data_home_with_search() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithTag(SearchField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SearchField).performTextInput("naruto")
        composeTestRule.waitUntil(5_000) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagListItem)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().assertExists()
    }

    @Test
    fun show_data_home_to_detail_and_back() {
        showDataHomeToDetail()
        composeTestRule.onNodeContentDescWithStringId(R.string.back).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.back).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun show_data_home_to_detail_and_addFavorite() {
        showDataHomeToDetail()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_inactive)
            .assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_inactive).performClick()
        composeTestRule.waitUntil(2_000) {
            return@waitUntil composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.to_favorite))
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithStringId(R.string.to_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        showAnimeListToDetail()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_active).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.favorite_active).performClick()
        composeTestRule.activityRule.scenario.onActivity { activity ->
            activity.onBackPressedDispatcher.onBackPressed()
        }
        navController.assertCurrentRouteName(Screen.Favorite.route)
        composeTestRule.onNodeWithTag(TagEmpty).assertIsDisplayed()
    }

    @Test
    fun show_data_favorite() {
        composeTestRule.activity.let {
            it.lifecycleScope.launch {
                AnimeDatabase.getInstance(it.applicationContext).animeDao().saveAnime(
                    AnimeEntity(
                        id = 41467,
                        title = "Bleach: Sennen Kessen-hen",
                        image = "https://cdn.myanimelist.net/images/anime/1764/126627.jpg",
                        type = "TV",
                        episodes = 13,
                        members = 298489,
                        score = 9.12
                    )
                )
            }
        }
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeContentDescWithStringId(R.string.menu_favorite).performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
        showAnimeListToDetail()
        composeTestRule.activity.let {
            it.lifecycleScope.launch {
                AnimeDatabase.getInstance(it.applicationContext).animeDao().deleteAllAnime()
            }
        }
    }

    @Test
    fun show_data_about() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeContentDescWithStringId(R.string.menu_about).performClick()
        navController.assertCurrentRouteName(Screen.About.route)
        composeTestRule.onNodeWithStringId(R.string.my_name).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.profile).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.dicoding).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.linkedin).assertIsDisplayed()
        composeTestRule.onNodeContentDescWithStringId(R.string.github).assertIsDisplayed()
    }

    private fun showDataHomeToDetail() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithTag(TagLoading).assertIsDisplayed()
        showAnimeListToDetail()
    }

    private fun showAnimeListToDetail() {
        composeTestRule.waitUntil(5_000) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagListItem)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().assertExists()
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.waitUntil(5_000) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagDetail)
                .fetchSemanticsNodes().size == 1
        }
    }
}