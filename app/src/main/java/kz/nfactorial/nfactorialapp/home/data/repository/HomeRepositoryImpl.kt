package kz.nfactorial.nfactorialapp.home.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.nfactorial.nfactorialapp.home.data.account.AccountProvider
import kz.nfactorial.nfactorialapp.home.data.dataSource.HomeRemoteDataSource
import kz.nfactorial.nfactorialapp.home.data.mappers.HomeComponentApiToDomainMapper
import kz.nfactorial.nfactorialapp.home.data.model.Account
import kz.nfactorial.nfactorialapp.home.domain.entity.HomeItem
import kz.nfactorial.nfactorialapp.home.domain.repository.entity.HomeRepository

class HomeRepositoryImpl(
    private val accountProvider: AccountProvider,
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeComponentApiToDomainMapper: HomeComponentApiToDomainMapper,
) : HomeRepository {

    fun getAccountInfo(): Flow<Account?> {
        return accountProvider.getAccount()
    }

    override fun getComponents(): Flow<List<HomeItem>> {
        return flow {
            val homeComponentsApi = homeRemoteDataSource.getHomeComponents()
            val homeItems = homeComponentApiToDomainMapper.invoke(homeComponentsApi)

            emit(homeItems)
        }
    }
}