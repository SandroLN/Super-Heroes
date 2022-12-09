package com.sandroln.heroesApp.ui

import com.sandroln.heroesApp.domain.model.HeroModel

data class HeroesState(
    val heroes: List<HeroModel> = emptyList(),
    val isLoading: Boolean = true,
    val message: String = "",
    val query: String? = ""
)