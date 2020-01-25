package com.nstudiosappdev.core.data.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.nstudiosappdev.core.data.db.Db

@Entity(
    tableName = Db.TABLES.CURRENCIES.NAME,
    primaryKeys = ["bankName", "currencyType"]
)
class CurrenciesEntity constructor(
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.BANK_NAME) @NonNull val bankName: String,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.BUY_PRICE) val buyPrice: String?,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.BUY_STATUS) val buyStatus: String?,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.SELL_PRICE) val sellPrice: String?,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.CURRENCY_TYPE) @NonNull val currencyType: String,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.SELL_STATUS) val sellStatus: String?,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.CREATE_DATE) val createDate: Long?,
    @ColumnInfo(name = Db.TABLES.CURRENCIES.COLUMNS.UPDATE_DATE) val updateDate: Long?
) : DbEntity
