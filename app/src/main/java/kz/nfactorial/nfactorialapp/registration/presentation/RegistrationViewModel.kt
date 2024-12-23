package kz.nfactorial.nfactorialapp.registration.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository

class RegistrationViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    var state: RegistrationState by mutableStateOf(RegistrationState(name = "", size = ""))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getProfile()
                .onSuccess { account ->
                    state = state.copy(
                        name = account.name,
                        size = account.size.toString(),
                    )
                }
        }
    }

    fun dispatch(event: RegistrationEvent, navController: NavController) {
        when (event) {
            is RegistrationEvent.OnNameChanged -> {
                state = state.copy(name = event.name)
            }
            is RegistrationEvent.OnSizeChanged -> {
                state = state.copy(size = event.size)
            }
            RegistrationEvent.OnNameChangeSaveClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    profileRepository.setName(state.name)
                }
            }
            RegistrationEvent.OnSizeChangeSaveClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val size = state.size.toIntOrNull() ?: return@launch
                    profileRepository.setSize(size)
                }
            }
            RegistrationEvent.OnBackClick -> {
                navController.popBackStack()
            }
        }
    }
}