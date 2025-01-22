package kz.nfactorial.nfactorialapp.photo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.nfactorial.nfactorialapp.registration.data.repository.ProfileRepository

class ChoosePhotoViewModel(
    val profileRepository: ProfileRepository,
): ViewModel() {

    private val _state = MutableStateFlow(ChoosePhotoState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ChoosePhotoEffect?>()
    val effect = _effect.asSharedFlow()

    init {
        profileRepository
            .getProfile()
            .onEach { account -> _state.update { it.copy(currentPhoto = account.image) }}
            .launchIn(viewModelScope)
    }

    fun dispatch(event: ChoosePhotoEvent) {
        when (event) {
            is ChoosePhotoEvent.OnCameraClick -> {
                viewModelScope.launch {
                    _effect.emit(ChoosePhotoEffect.OpenCamera())
                }
            }
            is ChoosePhotoEvent.OnGalleryClick -> {
                viewModelScope.launch {
                    _effect.emit(ChoosePhotoEffect.OpenGallery())
                }
            }
            is ChoosePhotoEvent.OnPhotoSelected -> {
                _state.update { it.copy(selectedPhoto = event.uri) }
            }
            is ChoosePhotoEvent.OnSubmitClick -> {
                val uri = _state.value.selectedPhoto ?: return
                viewModelScope.launch(Dispatchers.IO) {
                    profileRepository.setImage(uri)
                    _effect.emit(ChoosePhotoEffect.Close(uri))
                }
            }
        }
    }
}