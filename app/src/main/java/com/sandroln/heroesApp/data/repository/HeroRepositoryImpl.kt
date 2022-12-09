package com.sandroln.heroesApp.data.repository

import android.util.Log
import com.sandroln.heroesApp.common.Resource
import com.sandroln.heroesApp.data.db.local.HeroDao
import com.sandroln.heroesApp.data.db.remote.ApiService
import com.sandroln.heroesApp.data.mapper.HeroMapper
import com.sandroln.heroesApp.domain.model.HeroModel
import com.sandroln.heroesApp.domain.repository.HeroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeroRepositoryImpl @Inject constructor(
    private val mapper: HeroMapper,
    private val api: ApiService,
    private val heroDao: HeroDao
) : HeroRepository {


    override suspend fun getHeroes(): Flow<Resource<List<HeroModel>>> = flow {
        emit(Resource.Loading(true))
        val dbHeroes = heroDao.getHeroes().map { mapper.mapDbModelToModel(it) }
        if (dbHeroes.isEmpty()) {
            val apiHeroes = api.getAllHeroes()
            val heroes = apiHeroes.map { mapper.mapDtoToModel(it) }
            heroDao.insertHeroList(apiHeroes.map { mapper.mapDtoToDbModel(it) })
            emit(Resource.Loading(false))
            emit(Resource.Success(data = heroes))
        } else {
            emit(Resource.Loading(false))
            emit(Resource.Success(data = dbHeroes))
        }
    }.catch { emit(Resource.Error("Couldn't load data")) }


    override suspend fun getHero(id: Long): Flow<Resource<HeroModel>> = flow {
        emit(Resource.Loading(true))
        val dbHero = heroDao.getHeroById(id)
        val localHero = mapper.mapDbModelToModel(dbHero)
        emit(Resource.Loading(false))
        emit(Resource.Success(data = localHero))
    }.catch { emit(Resource.Error("Couldn't load data")) }
        .flowOn(Dispatchers.IO)


    override suspend fun loadData() {
        withContext(Dispatchers.IO) {
            if (heroDao.getHeroes().isEmpty()) {
                try {
                    val heroesList = api.getAllHeroes()
                    heroDao.insertHeroList(heroesList.map { mapper.mapDtoToDbModel(it) })
                } catch (e: Exception) {
                    Log.d("TAG", "Internet Exception")
                }
            }
        }
    }
}