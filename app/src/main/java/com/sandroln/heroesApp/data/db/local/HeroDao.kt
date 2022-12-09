package com.sandroln.heroesApp.data.db.local

import androidx.room.*
import com.sandroln.heroesApp.data.db.local.models.HeroDbModel


@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_table")
    suspend fun getHeroes(): List<HeroDbModel>

    @Query("SELECT * FROM hero_table WHERE id == :id LIMIT 1")
    suspend fun getHeroById(id: Long): HeroDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroList(priceList: List<HeroDbModel>)

    @Update
    suspend fun update(hero: HeroDbModel)
}