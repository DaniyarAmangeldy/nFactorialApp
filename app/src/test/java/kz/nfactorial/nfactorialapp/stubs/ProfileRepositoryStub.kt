package kz.nfactorial.nfactorialapp.stubs

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kz.nfactorial.nfactorialapp.registration.domain.ProfileRepository
import kz.nfactorial.nfactorialapp.registration.presentation.model.Account

class ProfileRepositoryStub(
    private val account: Account,
): ProfileRepository {

    override fun getProfile(): Flow<Account> {
        return flowOf(account)
    }

    override suspend fun setSize(size: Int): Flow<Account> {
        return flowOf(account)
    }

    override suspend fun setImage(image: Uri?) = Unit

    override suspend fun setName(name: String): Flow<Account> {
        return flowOf(account)
    }
}