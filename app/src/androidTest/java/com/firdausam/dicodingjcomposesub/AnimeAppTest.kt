package com.firdausam.dicodingjcomposesub

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.firdausam.dicodingjcomposesub.data.fakeEntity
import com.firdausam.dicodingjcomposesub.data.local.room.AnimeDatabase
import com.firdausam.dicodingjcomposesub.ui.navigation.Screen
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.*
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimeAppTest {

    private val internetTimeout = 5_000L

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
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            DicodingJetpackComposeSubmissionTheme {
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
        composeTestRule.waitUntil(internetTimeout) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagListItem)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().assertExists()
    }

    @Test
    fun show_data_home_with_search_not_found() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithTag(SearchField).assertIsDisplayed()
        composeTestRule.onNodeWithTag(SearchField).performTextInput("randomrandom")
        composeTestRule.waitUntil(internetTimeout) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagEmpty).fetchSemanticsNodes().size == 1
        }
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
    fun show_data_favorite_with_to_detail() {
        composeTestRule.activity.let {
            it.lifecycleScope.launch {
                AnimeDatabase.getInstance(it.applicationContext).animeDao().saveAnime(fakeEntity)
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

    private fun showDataHomeToDetail() {
        navController.assertCurrentRouteName(Screen.Home.route)
        composeTestRule.onNodeWithTag(TagLoading).assertIsDisplayed()
        showAnimeListToDetail()
    }

    private fun showAnimeListToDetail() {
        composeTestRule.waitUntil(internetTimeout) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagListItem)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().assertExists()
        composeTestRule.onNodeWithTag(TagListItem).onChildren().onFirst().performClick()
        navController.assertCurrentRouteName(Screen.Detail.route)
        composeTestRule.waitUntil(internetTimeout) {
            return@waitUntil composeTestRule.onAllNodesWithTag(TagDetail)
                .fetchSemanticsNodes().size == 1
        }
    }

    @After
    fun afterTest() {
        composeTestRule.activity.let {
            it.lifecycleScope.launch {
                AnimeDatabase.getInstance(it.applicationContext).animeDao().deleteAllAnime()
            }
        }
    }

}