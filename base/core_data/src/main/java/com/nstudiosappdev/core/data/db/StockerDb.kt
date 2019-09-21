package com.nstudiosappdev.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nstudiosappdev.core.data.db.dao.CurrenciesDao
import com.nstudiosappdev.core.data.db.entity.CurrenciesEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [CurrenciesEntity::class],
    version = Db.Config.DB_VERSION,
    exportSchema = true
)
abstract class StockerDb: RoomDatabase() {
    abstract fun currenciesDao(): CurrenciesDao
}