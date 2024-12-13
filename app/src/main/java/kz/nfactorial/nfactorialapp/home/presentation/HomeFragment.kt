package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.extensions.viewModels
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme

class HomeFragment: Fragment() {

    private val viewModel: HomeViewModel by viewModels(
        viewModelInitializer = {
            HomeViewModel(
                accountProvider = RoomAccountProvider(requireContext()),
                homeStateFactory = HomeStateFactory(),
            )
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            var timer = 0L
            while (true) {
                Log.d("Timer", timer.toString())
                timer += 1L
                delay(1000L)
            }
        }
    }

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
}