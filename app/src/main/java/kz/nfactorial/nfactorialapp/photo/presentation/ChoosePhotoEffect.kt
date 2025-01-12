package kz.nfactorial.nfactorialapp.photo.presentation

import android.net.Uri

sealed interface ChoosePhotoEffect {

    class OpenGallery: ChoosePhotoEffect

    class OpenCamera: ChoosePhotoEffect

    class Close(val result: Uri?): ChoosePhotoEffect
}