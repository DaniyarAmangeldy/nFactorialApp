package kz.nfactorial.nfactorialapp.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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

    var homeState by mutableStateOf(homeStateFactory.createInitialState())

    init {
        viewModelScope.launch {
            homeRepository.getHomeComponents()
                .onSuccess { components ->
                    val account = homeRepository.getAccountInfo()
                    homeState = homeState.copy(uiData = homeStateFactory.createUiData(components, account))
                }
        }
    }

    fun dispatch(event: HomeEvent, navController: NavController) {
        when (event) {
            is HomeEvent.OnFilterClick -> {
                homeState = homeState.copy(
                    selectedFilterIds = homeState.selectedFilterIds.addOrRemove(event.filter.id),
                )
            }
            is HomeEvent.OnSearchChanged -> {
                homeState = homeState.copy(
                    searchField = event.text,
                )
            }
            is HomeEvent.OnStoreClick -> {
                navController.navigate(HomeFragmentDirections.actionHomeToStore(event.store))
            }
            is HomeEvent.OnRegistrationClick -> {
                navController.navigate(HomeFragmentDirections.actionHomeToRegistration())
            }
            is HomeEvent.OnResume -> {
                viewModelScope.launch {
                    val account = homeRepository.getAccountInfo()
                    homeState = homeState.copy(
                        uiData = homeState.uiData?.copy(
                            account = AccountInfo(
                                fullName = account?.name.orEmpty(),
                                image = R.drawable.ic_spiderman
                            )
                        )
                    )
                }
            }
        }
    }
}