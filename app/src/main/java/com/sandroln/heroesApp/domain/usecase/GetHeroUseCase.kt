package com.sandroln.heroesApp.domain.usecase

import com.sandroln.heroesApp.domain.repository.HeroRepository
import javax.inject.Inject

class GetHeroUseCase @Inject constructor(
    private val repository: HeroRepository
) {
    suspend operator fun invoke(id: Long) = repository.getHero(id)
}