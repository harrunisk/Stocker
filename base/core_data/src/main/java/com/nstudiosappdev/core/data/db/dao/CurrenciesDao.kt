package com.nstudiosappdev.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nstudiosappdev.core.data.db.entity.CurrenciesEntity

@Dao
interface CurrenciesDao {
    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): List<CurrenciesEntity>

    @Query("SELECT * FROM currencies WHERE currencyType = :currencyType")
    fun getSpecificCurrencyTypes(currencyType: String): List<CurrenciesEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addCurrency(currenciesEntity: CurrenciesEntity): Long

    @Query("DELETE FROM currencies WHERE bankName = :bankName and currencyType = :currencyType")
    fun deleteCurrency(bankName: String, currencyType: String)
}