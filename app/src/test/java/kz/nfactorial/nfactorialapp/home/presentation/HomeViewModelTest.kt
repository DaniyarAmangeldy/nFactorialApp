package kz.nfactorial.nfactorialapp.home.presentation

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import app.cash.turbine.test
import junit.framework.TestCase.assertTrue
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kz.nfactorial.nfactorialapp.home.domain.usecases.GetHomeComponentsUseCase
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.registration.domain.ProfileRepository
import kz.nfactorial.nfactorialapp.registration.presentation.model.Account
import kz.nfactorial.nfactorialapp.rule.MainDispatcherRule
import kz.nfactorial.nfactorialapp.stubs.ProfileRepositoryStub
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito.atMostOnce
import org.mockito.kotlin.any
import org.mockito.kotlin.anyOrNull
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class HomeViewModelTest {

    @get:Rule
    val rule = MainDispatcherRule()

    @Test
    fun `check filter selection on home screen`() = runTest {
        val stubAccount = Account("Daniyar", 30, null)
        val player: Player = mock()
        val viewModel = HomeViewModel(
            profileRepository = ProfileRepositoryStub(stubAccount),
            homeStateFactory = HomeStateFactory(player),
            player = player,
            getHomeComponentsUseCase = GetHomeComponentsUseCase(homeRepository = mock())
        )

        viewModel.dispatch(HomeEvent.OnFilterClick(ChipItem(123, "chip")), navController = mock())

        viewModel.homeState.test {
            val item = awaitItem()
            assertTrue(123 in item.selectedFilterIds)
        }
    }

    @Test
    fun `check ad banner close on close click`() = runTest {
        val stubAccount = Account("Daniyar", 30, null)
        val player: Player = mock()
        val viewModel = HomeViewModel(
            profileRepository = ProfileRepositoryStub(stubAccount),
            homeStateFactory = HomeStateFactory(player),
            player = player,
            getHomeComponentsUseCase = GetHomeComponentsUseCase(mock())
        )

        viewModel.dispatch(HomeEvent.OnAdCloseClick, mock())

        viewModel.homeState.test {
            val item = awaitItem()
            assertEquals(expected = false, actual = item.isAdShow)
        }
    }

    @Test
    fun `check profile updated on home screen when image updated`() = runTest {
        val stubAccount = Account("Daniyar", 30, null)
        val player: Player = mock()
        val profileRepository = mockProfileRepository(stubAccount)
        val uiData = HomeState.UiData(account = null, emptyList(), emptyList(), MediaItem.EMPTY)
        val state = HomeState(uiData = uiData, searchField = "", selectedFilterIds = emptySet(), player = player)
        val homeStateFactory = mockHomeStateFactory(state, uiData)
        val viewModel = HomeViewModel(
            profileRepository = profileRepository,
            homeStateFactory = homeStateFactory,
            player = player,
            getHomeComponentsUseCase = mock(),
        )

        viewModel.dispatch(HomeEvent.OnImageUpdated, mock())

        viewModel.homeState.test {
            val initialItem = awaitItem()
            val imageUpdatedItem = awaitItem()
            verify(profileRepository).getProfile()
        }
    }

    private fun mockProfileRepository(account: Account): ProfileRepository {
        return mock {
            on { getProfile() } doReturn flowOf(account)
        }
    }

    private fun mockHomeStateFactory(state: HomeState, uiData: HomeState.UiData): HomeStateFactory {
        return mock {
            on { createInitialState() } doReturn state
            on { createUiData(anyList(), anyOrNull()) } doReturn uiData
        }
    }
}