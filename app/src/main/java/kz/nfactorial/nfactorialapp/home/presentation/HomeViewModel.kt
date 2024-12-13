package kz.nfactorial.nfactorialapp.home.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kz.nfactorial.nfactorialapp.R
import kz.nfactorial.nfactorialapp.extensions.CollectionExtensions.addOrRemove
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.model.Account
import kz.nfactorial.nfactorialapp.home.presentation.factory.HomeStateFactory
import kz.nfactorial.nfactorialapp.home.presentation.models.AccountInfo

class HomeViewModel(
    private val accountProvider: AccountProvider,
    homeStateFactory: HomeStateFactory,
) : ViewModel() {

    var homeState by mutableStateOf(homeStateFactory.createInitialState())

    init {
        viewModelScope.launch {
            val asyncExampleValue = viewModelScope.async {
                delay(5_000)
                "Hello World"
            }

            val result = asyncExampleValue.await()
            Log.d("HomeViewModel", "result: $result")

            val launchExample = launch {
                delay(5_000)
                "Hello World"
            }
        }
        runBlocking {
            // No need scope
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
                    val account = withContext(Dispatchers.Default) {
                        accountProvider.getAccount()?.toAccountInfo()
                    }
                    homeState = homeState.copy(account = account)
                }
            }
        }
    }

    private fun Account.toAccountInfo(): AccountInfo {
        return AccountInfo(
            fullName = name,
            image = R.drawable.ic_spiderman,
        )
    }
}