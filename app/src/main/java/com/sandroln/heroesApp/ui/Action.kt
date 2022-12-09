package com.sandroln.heroesApp.ui

import com.sandroln.heroesApp.domain.model.HeroModel

sealed class Action {
    data class Loading(val loading: Boolean) : Action()
    data class Success(val item: HeroModel) : Action()
    data class Error(val message: String) : Action()
}
