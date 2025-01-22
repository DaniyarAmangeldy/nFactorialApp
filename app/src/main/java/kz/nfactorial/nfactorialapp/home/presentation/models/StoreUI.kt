package kz.nfactorial.nfactorialapp.home.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreUI(
    val id: String,
    val name: String,
    val image: String,
    val rating: Rating,
    val products: List<Product> = emptyList(),
) : Parcelable

@Parcelize
data class Rating(
    val average: Float,
    val count: Int,
) : Parcelable