package kz.nfactorial.nfactorialapp.photo.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import kz.nfactorial.nfactorialapp.ui.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChoosePhotoActivity: FragmentActivity() {

    private val choosePhotoViewModel: ChoosePhotoViewModel by viewModel()

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