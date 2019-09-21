package com.nstudiosappdev.core.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS currencies (" +
                "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'bankName' TEXT," +
                "'buyPrice' TEXT," +
                "'buyStatus' TEXT," +
                "'sellPrice' TEXT," +
                "'sellStatus' TEXT," +
                "'currencyType' TEXT," +
                "'createdDate' LONG," +
                "'updatedDate' LONG )  ")
    }
}