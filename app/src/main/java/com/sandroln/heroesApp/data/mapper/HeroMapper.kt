package com.sandroln.heroesApp.data.mapper

import com.sandroln.heroesApp.data.db.local.models.HeroDbModel
import com.sandroln.heroesApp.data.db.remote.model.HeroDTO
import com.sandroln.heroesApp.domain.model.HeroModel
import javax.inject.Inject

class HeroMapper @Inject constructor() {

    fun mapDtoToDbModel(dto: HeroDTO): HeroDbModel = HeroDbModel(
        id = dto.id.toLong(),
        name = dto.name,
        images = dto.images.lg,
        combat = dto.powerstats.combat,
        durability = dto.powerstats.durability,
        intelligence = dto.powerstats.intelligence,
        power = dto.powerstats.power,
        speed = dto.powerstats.speed,
        strength = dto.powerstats.strength,
        height = dto.appearance.height,
        weight = dto.appearance.weight
    )

    fun mapDbModelToModel(heroDbModel: HeroDbModel) = HeroModel(
        id = heroDbModel.id,
        name = heroDbModel.name,
        images = heroDbModel.images,
        combat = heroDbModel.combat,
        durability = heroDbModel.durability,
        intelligence = heroDbModel.intelligence,
        power = heroDbModel.power,
        speed = heroDbModel.speed,
        strength = heroDbModel.strength,
        height = heroDbModel.height,
        weight = heroDbModel.weight
    )

    fun mapDtoToModel(dto: HeroDTO): HeroModel = HeroModel(
        id = dto.id.toLong(),
        name = dto.name,
        images = dto.images.lg,
        combat = dto.powerstats.combat,
        durability = dto.powerstats.durability,
        intelligence = dto.powerstats.intelligence,
        power = dto.powerstats.power,
        speed = dto.powerstats.speed,
        strength = dto.powerstats.strength,
        height = dto.appearance.height,
        weight = dto.appearance.weight
    )
}