package kz.nfactorial.nfactorialapp.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.extensions.CollectionExtensions.addOrRemove
import kz.nfactorial.nfactorialapp.home.data.repository.HomeRepository
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val profileRepository: ProfileRepository,
    private val homeStateFactory: HomeStateFactory,
) : ViewModel() {

    private val _homeState = MutableStateFlow(homeStateFactory.createInitialState())
    val homeState = _homeState.asStateFlow()

    private val _effect = MutableSharedFlow<HomeEffect?>()
    val effect = _effect.asSharedFlow()

    fun dispatch(event: HomeEvent, navController: NavController) {
        when (event) {
            is HomeEvent.OnFilterClick -> {
                _homeState.update {
                    it.copy(selectedFilterIds = it.selectedFilterIds.addOrRemove(event.filter.id))
                }
            }
            is HomeEvent.OnAdCloseClick -> {
                _homeState.update { it.copy(isAdShow = false) }
            }
            is HomeEvent.OnAdClick -> {
                _homeState.update { it.copy(isAdShow = true) }
            }
            is HomeEvent.OnConnectionAvailable -> {
                _homeState.update { it.copy(connectionLostBannerVisible = false) }
            }
            is HomeEvent.OnConnectionLost -> {
                _homeState.update { it.copy(connectionLostBannerVisible = true) }
            }
            is HomeEvent.OnProfileImageClick -> {
                viewModelScope.launch {
                    _effect.emit(HomeEffect.OpenChooseImage())
                }
            }
            is HomeEvent.OnImageUpdated -> {
                val uiState = _homeState.value.uiData ?: return
                viewModelScope.launch {
                    profileRepository.getProfile().take(1).collect { account ->
                        _homeState.update { it.copy(uiData = homeStateFactory.updateProfile(uiState, account)) }
                    }
                }
            }
            is HomeEvent.OnSearchChanged -> {
                _homeState.update { it.copy(searchField = event.text) }
            }
            is HomeEvent.OnCreate -> {
                viewModelScope.launch {
                    homeRepository.getHomeComponents()
                        .combine(profileRepository.getProfile(), ::Pair)
                        .catch { error -> Log.e(TAG, "Error during load home components: $error")}
                        .collect { (components, account) ->
                            _homeState.update { it.copy(uiData = homeStateFactory.createUiData(components, account)) }
                        }
                }
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