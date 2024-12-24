package kz.nfactorial.nfactorialapp.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.extensions.CollectionExtensions.addOrRemove
import kz.nfactorial.nfactorialapp.home.data.repository.HomeRepository
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo

class HomeViewModel(
    private val homeRepository: HomeRepository,
    homeStateFactory: HomeStateFactory,
) : ViewModel() {

    private val _homeState = MutableStateFlow(homeStateFactory.createInitialState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            homeRepository.getHomeComponents()
                .combine(homeRepository.getAccountInfo(), ::Pair)
                .catch { error -> Log.e(TAG, "Error during load home components: $error")}
                .collect { (components, account) ->
                    _homeState.update { it.copy(uiData = homeStateFactory.createUiData(components, account)) }
                }
        }
    }

    fun dispatch(event: HomeEvent, navController: NavController) {
        when (event) {
            is HomeEvent.OnFilterClick -> {
                _homeState.update {
                    it.copy(selectedFilterIds = it.selectedFilterIds.addOrRemove(event.filter.id))
                }
            }
            is HomeEvent.OnSearchChanged -> {
                _homeState.update { it.copy(searchField = event.text) }
            }
            is HomeEvent.OnStoreClick -> {
                navController.navigate(HomeFragmentDirections.actionHomeToStore(event.store))
            }
            is HomeEvent.OnRegistrationClick -> {
                navController.navigate(HomeFragmentDirections.actionHomeToRegistration())
            }
        }
    }

    private companion object {

        const val TAG = "HomeViewModel"
    }
}