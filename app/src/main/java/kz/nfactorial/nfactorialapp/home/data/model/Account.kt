package kz.nfactorial.nfactorialapp.home.data.model

import android.net.Uri

data class Account(
    val name: String,
    val size: Int?,
    val image: Uri?,
)