package kz.nfactorial.nfactorialapp.home.domain.repository.entity

import kotlinx.coroutines.flow.Flow
import kz.nfactorial.nfactorialapp.home.domain.entity.HomeItem

interface HomeRepository {

    fun getComponents(): Flow<List<HomeItem>>
}