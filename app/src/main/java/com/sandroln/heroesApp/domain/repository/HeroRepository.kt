package com.sandroln.heroesApp.domain.repository

import com.sandroln.heroesApp.common.Resource
import com.sandroln.heroesApp.domain.model.HeroModel
import kotlinx.coroutines.flow.Flow

interface HeroRepository {

    suspend fun getHeroes(): Flow<Resource<List<HeroModel>>>

    suspend fun getHero(id: Long): Flow<Resource<HeroModel>>

    suspend fun loadData()
}