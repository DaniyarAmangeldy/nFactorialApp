package kz.nfactorial.nfactorialapp.search.presentation

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
import kz.nfactorial.nfactorialapp.search.data.repository.ProductRepository
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import retrofit2.create

class SearchFragment: Fragment() {

    private val searchViewModel: SearchViewModel by viewModels(
        viewModelInitializer = {
            SearchViewModel(
                productRepository = ProductRepository(
                    ApiClient(requireContext()).retrofit.create(),
                ),
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
                val state by searchViewModel.state.collectAsState()
                SearchScreen(
                    state = state,
                    onEvent = searchViewModel::dispatch
                )
            }
        }
    }

}