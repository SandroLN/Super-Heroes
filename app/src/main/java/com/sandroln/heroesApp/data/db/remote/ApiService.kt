package com.sandroln.heroesApp.data.db.remote

import com.sandroln.heroesApp.data.db.remote.model.HeroDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all.json")
    suspend fun getAllHeroes(): List<HeroDTO>

    @GET("id/{heroId}")
    suspend fun getHeroById(@Path("heroId") id: Int): HeroDTO
}



