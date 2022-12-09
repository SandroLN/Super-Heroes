package com.sandroln.heroesApp.di

import android.app.Application
import com.sandroln.heroesApp.data.db.local.HeroDao
import com.sandroln.heroesApp.data.db.local.HeroDatabase
import com.sandroln.heroesApp.data.db.remote.ApiFactory
import com.sandroln.heroesApp.data.db.remote.ApiService
import com.sandroln.heroesApp.data.repository.HeroRepositoryImpl
import com.sandroln.heroesApp.domain.repository.HeroRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindHeroRepository(impl: HeroRepositoryImpl): HeroRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): HeroDao {
            return HeroDatabase.invoke(application).heroDao()
        }

        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
