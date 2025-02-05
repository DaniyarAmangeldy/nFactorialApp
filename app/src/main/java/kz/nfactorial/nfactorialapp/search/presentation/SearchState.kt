package kz.nfactorial.nfactorialapp.search.presentation

import kz.nfactorial.nfactorialapp.home.presentation.models.Product

data class SearchState(
    val searchField: String,
    val allProducts: List<Product>,
    val uiState: UiState,
) {

    sealed interface UiState {

        data object Loading : UiState

        data class Data(val items: List<Product>) : UiState
    }
}