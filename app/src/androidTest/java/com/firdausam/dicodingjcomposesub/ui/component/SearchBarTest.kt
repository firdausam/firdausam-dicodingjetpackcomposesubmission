package com.firdausam.dicodingjcomposesub.ui.component

import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.firdausam.dicodingjcomposesub.data.InformationTest
import com.firdausam.dicodingjcomposesub.ui.theme.DicodingJetpackComposeSubmissionTheme
import com.firdausam.dicodingjcomposesub.util.SearchField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            var searchValue by remember {
                mutableStateOf("")
            }
            DicodingJetpackComposeSubmissionTheme {
                SearchBar(
                    searchValue = searchValue
                ) {
                    searchValue = it
                }
            }
        }
    }

    @Test
    fun searchBar_Test() {
        composeTestRule.onNodeWithTag(SearchField).assertIsDisplayed()
        composeTestRule.onNodeWithText("").assertIsDisplayed()
        composeTestRule.onNodeWithTag(SearchField).performTextInput(InformationTest)
        composeTestRule.onNodeWithText(InformationTest).assertIsDisplayed()
    }
}