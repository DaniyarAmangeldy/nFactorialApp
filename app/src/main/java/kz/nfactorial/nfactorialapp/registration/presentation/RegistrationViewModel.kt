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

class RegistrationViewModel(
    private val accountProvider: AccountProvider,
) : ViewModel() {

    var state: RegistrationState by mutableStateOf(RegistrationState(name = "", size = ""))

    init {
        viewModelScope.launch {
            val account = withContext(Dispatchers.IO) { accountProvider.getAccount() }
            state = state.copy(
                name = account?.name.orEmpty(),
                size = account?.size?.toString().orEmpty(),
            )
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
                    accountProvider.setName(state.name)
                }
            }
            RegistrationEvent.OnSizeChangeSaveClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    accountProvider.setSize(state.size.toIntOrNull())
                }
            }
            RegistrationEvent.OnBackClick -> {
                navController.popBackStack()
            }
        }
    }
}