package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.nfactorialapp.data.api.client.ApiClient
import kz.nfactorial.nfactorialapp.extensions.viewModels
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.home.data.repository.HomeRepository
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import retrofit2.create

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels(
        viewModelInitializer = {
            val apiClient = ApiClient(requireContext())
            val accountProvider = RoomAccountProvider(requireContext())
            HomeViewModel(
                homeRepository = HomeRepository(
                    apiClient = apiClient,
                    accountProvider = accountProvider,
                ),
                homeStateFactory = HomeStateFactory(),
                profileRepository = ProfileRepository(
                    profileApiService = apiClient.retrofit.create(),
                    accountProvider = accountProvider,
                )
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val navController = findNavController()
        setContent {
            AppTheme {
                val state by viewModel.homeState.collectAsState()
                HomeScreen(
                    state = state,
                    onEvent = { event -> viewModel.dispatch(event, navController) }
                )
            }
        }
    }
}