package kz.nfactorial.nfactorialapp.photo.presentation

import android.net.Uri

data class ChoosePhotoState(
    val currentPhoto: Uri? = null,
    val selectedPhoto: Uri? = null,
)