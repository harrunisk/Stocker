package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.data.db.StockerDb
import com.nstudiosappdev.core.data.db.entity.CurrenciesEntity
import com.nstudiosappdev.core.data.db.entity.DbEntityMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import java.lang.Exception
import javax.inject.Inject

class CurrenciesLocalDataSource @Inject constructor(
    private val db: StockerDb,
    private val mapper: DbEntityMapper<CurrenciesEntity, Currency>
) : DataSource.LocalDataSource<Long, Currency>
{
    override fun get(key: Long): Currency? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(page: Int): List<Currency> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(key: String): List<Currency> {
        val entities = db.currenciesDao().getSpecificCurrencyTypes(key)
        val currencies = ArrayList<Currency>()
        for (entity in entities) {
            currencies.add(mapper.map(entity))
        }
        return currencies
    }

    override fun get(key1: String, key2: String): Currency {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<Currency> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(key: Long?, data: Currency): Boolean {
        val currencyId = db.currenciesDao().addCurrency(mapper.map(data))
        return currencyId > 0
    }

    override fun remove(key1: String, key2: String): Boolean {
        return try {
            db.currenciesDao().deleteCurrency(
                bankName = key1,
                currencyType = key2
            )
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun remove(value: Currency): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeByKey(key: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clear() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}