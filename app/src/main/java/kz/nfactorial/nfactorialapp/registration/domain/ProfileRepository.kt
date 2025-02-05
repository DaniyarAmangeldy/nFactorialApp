package kz.nfactorial.nfactorialapp.registration.domain

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kz.nfactorial.nfactorialapp.registration.presentation.model.Account

interface ProfileRepository {

    fun getProfile(): Flow<Account>

    suspend fun setSize(size: Int): Flow<Account>

    suspend fun setImage(image: Uri?)

    suspend fun setName(name: String): Flow<Account>
}