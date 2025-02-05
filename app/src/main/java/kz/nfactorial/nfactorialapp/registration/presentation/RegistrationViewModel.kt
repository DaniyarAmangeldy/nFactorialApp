package kz.nfactorial.nfactorialapp.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepositoryImpl
import kz.nfactorial.nfactorialapp.registration.domain.ProfileRepository

class RegistrationViewModel(
    private val profileRepository: ProfileRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(RegistrationState(name = "", size = ""))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            profileRepository.getProfile()
                .flowOn(Dispatchers.IO)
                .collect { account ->
                    _state.update {
                        it.copy(
                            name = account.name,
                            size = account.size.toString(),
                        )
                    }
                }
        }
    }

    fun dispatch(event: RegistrationEvent, navController: NavController) {
        when (event) {
            is RegistrationEvent.OnNameChanged -> {
                _state.update {
                    it.copy(name = event.name)
                }
            }
            is RegistrationEvent.OnSizeChanged -> {
                _state.update { it.copy(size = event.size) }
            }
            RegistrationEvent.OnNameChangeSaveClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    profileRepository.setName(state.value.name)
                }
            }
            RegistrationEvent.OnSizeChangeSaveClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val size = state.value.size.toIntOrNull() ?: return@launch
                    profileRepository.setSize(size)
                }
            }
            RegistrationEvent.OnBackClick -> {
                navController.popBackStack()
            }
        }
    }
}