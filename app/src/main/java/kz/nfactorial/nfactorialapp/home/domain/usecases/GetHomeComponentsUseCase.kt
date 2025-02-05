package kz.nfactorial.nfactorialapp.home.domain.usecases

import kotlinx.coroutines.flow.Flow
import kz.nfactorial.nfactorialapp.home.domain.entity.HomeItem
import kz.nfactorial.nfactorialapp.home.domain.repository.entity.HomeRepository

class GetHomeComponentsUseCase(
    private val homeRepository: HomeRepository
) {

    operator fun invoke(): Flow<List<HomeItem>> {
        return homeRepository.getComponents()
    }
}