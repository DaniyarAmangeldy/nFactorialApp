package kz.nfactorial.nfactorialapp.home.presentation

import kz.nfactorial.nfactorialapp.home.presentation.models.ChipItem
import kz.nfactorial.nfactorialapp.home.presentation.models.Store

sealed interface HomeEvent {

    data class OnStoreClick(val store: Store) : HomeEvent

    data class OnSearchChanged(val text: String) : HomeEvent

    data class OnFilterClick(val filter: ChipItem) : HomeEvent

    data object OnRegistrationClick : HomeEvent

    data object OnAdCloseClick : HomeEvent

    data object OnCreate : HomeEvent

    data object OnAdClick : HomeEvent

    data object OnImageUpdated : HomeEvent

    data object OnConnectionAvailable : HomeEvent

    data object OnConnectionLost : HomeEvent

    data object OnProfileImageClick : HomeEvent
}