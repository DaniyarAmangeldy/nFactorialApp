package kz.nfactorial.nfactorialapp.registration.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequestApi(
    @SerialName("name")
    val name: String? = null,

    @SerialName("size")
    val size: String? = null,
)