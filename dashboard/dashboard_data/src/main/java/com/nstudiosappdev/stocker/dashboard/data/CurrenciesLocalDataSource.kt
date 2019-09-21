package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.data.db.StockerDb
import com.nstudiosappdev.core.data.db.entity.CurrenciesEntity
import com.nstudiosappdev.core.data.db.entity.DbEntityMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency
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

    override fun getAll(): List<Currency> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun put(key: Long?, data: Currency): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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