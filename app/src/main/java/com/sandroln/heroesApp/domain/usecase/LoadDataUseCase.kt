package com.sandroln.heroesApp.domain.usecase

import com.sandroln.heroesApp.domain.repository.HeroRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: HeroRepository
) {
    suspend operator fun invoke() = repository.loadData()
}
