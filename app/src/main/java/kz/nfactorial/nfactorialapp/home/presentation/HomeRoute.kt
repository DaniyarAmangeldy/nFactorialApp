package kz.nfactorial.nfactorialapp.home.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed interface HomeRoute: Parcelable {

    @Parcelize
    data object Avatar : HomeRoute
}