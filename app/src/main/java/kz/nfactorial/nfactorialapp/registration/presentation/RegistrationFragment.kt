package kz.nfactorial.nfactorialapp.registration.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.nfactorialapp.data.api.client.ApiClient
import kz.nfactorial.nfactorialapp.extensions.viewModels
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import retrofit2.create

class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels(
        viewModelInitializer = {
            RegistrationViewModel(
                profileRepository = ProfileRepository(
                    profileApiService = ApiClient(requireContext()).retrofit.create(),
                    accountProvider = RoomAccountProvider(requireContext())
                ),
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                val state by viewModel.state.collectAsState()
                RegistrationScreen(
                    state = state,
                    onEvent = { event -> viewModel.dispatch(event, findNavController()) }
                )
            }
        }
    }
}