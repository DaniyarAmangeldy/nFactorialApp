package kz.nfactorial.nfactorialapp.registration.data.api

import kz.nfactorial.nfactorialapp.home.data.model.ProfileApi
import kz.nfactorial.nfactorialapp.registration.data.model.ProfileRequestApi
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApiService {

    @GET("profile")
    suspend fun getProfile(): Result<ProfileApi>

    @POST("profile")
    suspend fun updateProfile(
        @Body requestBody: ProfileRequestApi
    ): Result<ProfileApi>
}