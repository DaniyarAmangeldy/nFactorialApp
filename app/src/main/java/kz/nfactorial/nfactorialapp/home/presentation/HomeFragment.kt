package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.nfactorialapp.extensions.viewModels
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.home.data.account.SharedPreferencesAccountProvider
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels(
        viewModelInitializer = {
            HomeViewModel(accountProvider = RoomAccountProvider(requireContext()))
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
                HomeScreen(
                    state = viewModel.homeState,
                    onEvent = { event -> viewModel.dispatch(event, navController) }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.dispatch(HomeEvent.OnResume, findNavController())
    }
}