package kz.nfactorial.nfactorialapp.home.presentation.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: Price,
    val id: String,
    val image: String,
) : Parcelable

@Parcelize
data class Price(
    val value: Int,
    val currency: String,
) : Parcelable



val Price.displayText: String
    get() = "${currency}$value"