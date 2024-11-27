package kz.nfactorial.nfactorialapp.registration.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.nfactorialapp.extensions.viewModels
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.home.data.account.SharedPreferencesAccountProvider
import kz.nfactorial.nfactorialapp.room.DatabaseHolder
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class RegistrationFragment : Fragment() {

    private val viewModel: RegistrationViewModel by viewModels(
        viewModelInitializer = {
            RegistrationViewModel(accountProvider = RoomAccountProvider(requireContext()))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = ComposeView(requireContext()).apply {
        setContent {
            AppTheme {
                RegistrationScreen(
                    state = viewModel.state,
                    onEvent = { event -> viewModel.dispatch(event, findNavController()) }
                )
            }
        }
    }
}