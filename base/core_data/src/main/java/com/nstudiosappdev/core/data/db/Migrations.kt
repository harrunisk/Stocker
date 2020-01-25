package com.nstudiosappdev.core.data.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS currencies (" +
                    "'bankName' TEXT PRIMARY KEY NOT NULL," +
                    "'buyPrice' TEXT," +
                    "'buyStatus' TEXT," +
                    "'sellPrice' TEXT," +
                    "'sellStatus' TEXT," +
                    "'currencyType ' TEXT PRIMARY KEY NOT NULL," +
                    "'createdDate' LONG," +
                    "'updatedDate' LONG )  "
        )
    }
}
