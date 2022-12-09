package com.sandroln.heroesApp.di

import android.app.Application
import com.sandroln.heroesApp.ui.HeroesApp
import com.sandroln.heroesApp.ui.MainActivity
import com.sandroln.heroesApp.ui.fragments.HeroesFragment
import com.sandroln.heroesApp.ui.fragments.HeroesFragmentDetail
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: HeroesFragment)

    fun inject(fragmentDetail: HeroesFragmentDetail)

    fun inject(heroesApp: HeroesApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
