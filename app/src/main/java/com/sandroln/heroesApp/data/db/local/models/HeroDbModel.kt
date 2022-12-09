package com.sandroln.heroesApp.data.db.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_table")
data class HeroDbModel(
    @PrimaryKey(autoGenerate = false) var id: Long,
    var name: String,
    var images: String,
    val height: List<String>,
    val weight: List<String>,
    val combat: Int,
    val durability: Int,
    val intelligence: Int,
    val power: Int,
    val speed: Int,
    val strength: Int
)