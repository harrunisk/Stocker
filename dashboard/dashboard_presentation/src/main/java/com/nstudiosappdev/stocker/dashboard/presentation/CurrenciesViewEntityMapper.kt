package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.presentation.entity.ViewEntityMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency

class CurrenciesViewEntityMapper : ViewEntityMapper<Currency, CurrenciesViewEntity> {

    override fun map(value: Currency): CurrenciesViewEntity {
        return CurrenciesViewEntity(
            value.bankName,
            value.buyPrice,
            value.buyStatus,
            value.sellPrice,
            value.sellStatus
        )
    }
}