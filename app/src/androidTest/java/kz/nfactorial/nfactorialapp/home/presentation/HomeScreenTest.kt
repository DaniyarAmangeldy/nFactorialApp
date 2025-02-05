package kz.nfactorial.nfactorialapp.home.presentation

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.test.platform.app.InstrumentationRegistry
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkHeaderNameVisible() {
        val state = HomeState(
            searchField = "",
            selectedFilterIds = emptySet(),
            uiData = HomeState.UiData(
                account = AccountInfo("Daniyar", null),
                filters = emptyList(),
                storeUI = emptyList(),
                mediaItem = MediaItem.EMPTY
            ),
            player = ExoPlayer.Builder(InstrumentationRegistry.getInstrumentation().targetContext).build(),
        )

        composeTestRule.setContent {
            AppTheme {
                HomeScreen(state = state, onEvent = {}, effect = null)
            }
        }

        composeTestRule
            .onNodeWithTag("home_list")
            .performScrollToNode(hasText("Daniyar"))
            .assertIsDisplayed()
    }

    @Test
    fun checkAdBottomSheetVisible() {
        val state = HomeState(
            searchField = "",
            selectedFilterIds = emptySet(),
            isAdShow = true,
            uiData = HomeState.UiData(
                account = AccountInfo("Daniyar", null),
                filters = emptyList(),
                storeUI = emptyList(),
                mediaItem = MediaItem.EMPTY,
            ),
            player = ExoPlayer.Builder(InstrumentationRegistry.getInstrumentation().targetContext).build(),

            )
        composeTestRule.setContent {
            AppTheme {
                HomeScreen(state = state, onEvent = {}, effect = null)
            }
        }

        composeTestRule
            .onNodeWithTag("ad_bottom_sheet")
            .assertIsDisplayed()
    }
}