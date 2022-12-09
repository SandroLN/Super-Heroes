package com.sandroln.heroesApp.ui

import android.app.Application
import com.sandroln.heroesApp.di.DaggerApplicationComponent

class HeroesApp : Application(){

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}