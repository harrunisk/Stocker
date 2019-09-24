package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.db.entity.CurrenciesEntity
import com.nstudiosappdev.core.data.db.entity.DbEntityMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency

class CurrenciesDbEntityMapper : DbEntityMapper<CurrenciesEntity, Currency> {

    override fun map(domainObject: Currency): CurrenciesEntity {
        return CurrenciesEntity(
            bankName = domainObject.bankName!!,
            buyPrice = domainObject.buyPrice,
            buyStatus = domainObject.buyStatus,
            sellPrice = domainObject.sellPrice,
            sellStatus = domainObject.sellStatus,
            currencyType = domainObject.currencyType!!,
            createDate = null,
            updateDate = null
        )
    }

    override fun map(entity: CurrenciesEntity): Currency {
        return Currency(
            bankName = entity.bankName,
            buyPrice = entity.buyPrice,
            buyStatus = entity.buyStatus,
            sellPrice = entity.sellPrice,
            sellStatus = entity.sellStatus,
            currencyType = entity.currencyType
        )
    }
}