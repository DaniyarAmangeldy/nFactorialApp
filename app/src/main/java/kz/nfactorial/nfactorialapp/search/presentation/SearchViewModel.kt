package kz.nfactorial.nfactorialapp.search.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.search.data.repository.ProductRepository

class SearchViewModel(
    private val productRepository: ProductRepository,
): ViewModel() {

    private val _state = MutableStateFlow(SearchState(
        searchField = "",
        uiState = SearchState.UiState.Loading,
        allProducts = emptyList(),
    ))
    val state = _state.asStateFlow()

    private val searchFlow = MutableStateFlow("")

    init {
        searchFlow
            .debounce(DEBOUNCE_MS)
            .distinctUntilChanged()
            .filter { query -> query.isNotBlank() }
            .onEach {
                _state.update { it.copy(uiState = SearchState.UiState.Loading) }
                delay(500L)
            }
            .flatMapLatest { query -> productRepository.searchProductByQuery(query = query) }
            .onEach { products -> _state.update { it.copy(uiState = SearchState.UiState.Data(products)) } }
            .catch { error -> Log.d(TAG, "Error while getting products list $error") }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            productRepository.getProducts()
                .catch { error -> Log.d(TAG, "Error while getting products list $error") }
                .collect { products ->
                    _state.update {
                        it.copy(
                            allProducts = products,
                            uiState = SearchState.UiState.Data(products))
                    }
                }
        }
    }

    fun dispatch(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchChanged -> {
                _state.update { it.copy(searchField = event.query) }
                searchFlow.value = event.query
                if (event.query.isEmpty()) {
                    _state.update { it.copy(uiState = SearchState.UiState.Data(_state.value.allProducts)) }
                }
            }
        }
    }

    private companion object {

        const val TAG = "SearchViewModel"
        const val DEBOUNCE_MS = 500L
    }
}