package kz.nfactorial.nfactorialapp.home.presentation.models

import androidx.annotation.DrawableRes

data class Banner(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
)