package com.sandroln.heroesApp.domain.model

data class HeroModel(
    val id: Long,
    val name: String,
    val images: String,
    val height: List<String>,
    val weight: List<String>,
    val combat: Int,
    val durability: Int,
    val intelligence: Int,
    val power: Int,
    val speed: Int,
    val strength: Int
)