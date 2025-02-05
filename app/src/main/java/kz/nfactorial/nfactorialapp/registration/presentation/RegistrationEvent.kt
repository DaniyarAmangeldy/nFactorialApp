package kz.nfactorial.nfactorialapp.registration.presentation

sealed interface RegistrationEvent {

    data class OnSizeChanged(val size: String) : RegistrationEvent

    data class OnNameChanged(val name: String) : RegistrationEvent

    data object OnBackClick : RegistrationEvent

    data object OnNameChangeSaveClicked : RegistrationEvent

    data object OnSizeChangeSaveClicked : RegistrationEvent
}