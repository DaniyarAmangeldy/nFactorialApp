package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment: Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val navController = findNavController()
        setContent {
            AppTheme {
                val state by homeViewModel.homeState.collectAsState()
                val effect by homeViewModel.effect.collectAsState(null)
                HomeScreen(
                    state = state,
                    onEvent = { event -> homeViewModel.dispatch(event, navController) },
                    effect = effect,
                )

                LaunchedEffect(Unit) {
                    val route = HomeFragmentArgs.fromBundle(requireArguments()).route
                    if (route != null) {
                        homeViewModel.dispatch(HomeEvent.OnNavigateToScreen(route), navController)
                    }
                }
            }
        }
    }
}