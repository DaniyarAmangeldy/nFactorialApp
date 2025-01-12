package kz.nfactorial.nfactorialapp.photo.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import kz.nfactorial.nfactorialapp.data.api.client.ApiClient
import kz.nfactorial.nfactorialapp.extensions.viewModels
import kz.nfactorial.nfactorialapp.home.data.account.RoomAccountProvider
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import retrofit2.create

class ChoosePhotoActivity: FragmentActivity() {

    private val choosePhotoViewModel: ChoosePhotoViewModel by viewModels {
        ChoosePhotoViewModel(
            profileRepository = ProfileRepository(
                profileApiService = ApiClient(this).retrofit.create(),
                accountProvider = RoomAccountProvider(this)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val state by choosePhotoViewModel.state.collectAsState()
            val effect by choosePhotoViewModel.effect.collectAsState(null)
            AppTheme {
                ChoosePhotoScreen(
                    state = state,
                    onEvent = choosePhotoViewModel::dispatch,
                    effect = effect,
                )
            }
        }
    }
}