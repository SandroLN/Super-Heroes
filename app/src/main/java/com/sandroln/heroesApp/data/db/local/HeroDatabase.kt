package com.sandroln.heroesApp.data.db.local

import android.content.Context
import androidx.room.*
import com.sandroln.heroesApp.data.db.local.models.ArrayListConverter
import com.sandroln.heroesApp.data.db.local.models.HeroDbModel


@Database(
    entities = [HeroDbModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ArrayListConverter::class)
abstract class HeroDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: HeroDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HeroDatabase::class.java,
                "hero_db.db"
            ).build()
    }

    abstract fun heroDao(): HeroDao
}