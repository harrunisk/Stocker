package com.nstudiosappdev.core.data.modules

import android.content.Context
import androidx.room.Room
import com.nstudiosappdev.core.data.db.Db
import com.nstudiosappdev.core.data.db.MIGRATION_1_2
import com.nstudiosappdev.core.data.db.StockerDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): StockerDb = Room.databaseBuilder(
        context,
        StockerDb::class.java, Db.Config.DB_NAME
    ).addMigrations(MIGRATION_1_2).build()
}
