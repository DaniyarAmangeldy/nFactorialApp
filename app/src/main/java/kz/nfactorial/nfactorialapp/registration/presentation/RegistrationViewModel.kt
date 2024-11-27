package kz.nfactorial.nfactorialapp.registration.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider

class RegistrationViewModel(
    private val accountProvider: AccountProvider,
) : ViewModel() {
    val account by lazy { accountProvider.getAccount() }

    var state: RegistrationState by mutableStateOf(
        RegistrationState(
            name = account?.name.orEmpty(),
            size = account?.size?.toString().orEmpty()
        )
    )

    fun dispatch(event: RegistrationEvent, navController: NavController) {
        when (event) {
            is RegistrationEvent.OnNameChanged -> {
                state = state.copy(name = event.name)
            }
            is RegistrationEvent.OnSizeChanged -> {
                state = state.copy(size = event.size)
            }
            RegistrationEvent.OnNameChangeSaveClicked -> {
                accountProvider.setName(state.name)
            }
            RegistrationEvent.OnSizeChangeSaveClicked -> {
                accountProvider.setSize(state.size.toIntOrNull())
            }
            RegistrationEvent.OnBackClick -> {
                navController.popBackStack()
            }
        }
    }
}