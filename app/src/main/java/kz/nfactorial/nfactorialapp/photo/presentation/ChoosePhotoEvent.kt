package kz.nfactorial.nfactorialapp.photo.presentation

import android.net.Uri

sealed interface ChoosePhotoEvent {

    data object OnGalleryClick: ChoosePhotoEvent

    data object OnCameraClick: ChoosePhotoEvent

    data object OnSubmitClick: ChoosePhotoEvent

    data class OnPhotoSelected(val uri: Uri?): ChoosePhotoEvent
}