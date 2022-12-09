package com.sandroln.heroesApp.di

import androidx.lifecycle.ViewModel
import com.sandroln.heroesApp.ui.viewmodels.HeroDetailViewModel
import com.sandroln.heroesApp.ui.viewmodels.HeroesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HeroesListViewModel::class)
    fun bindHeroesListViewModel(viewModel: HeroesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HeroDetailViewModel::class)
    fun bindHeroViewModel(viewModel: HeroDetailViewModel): ViewModel
}