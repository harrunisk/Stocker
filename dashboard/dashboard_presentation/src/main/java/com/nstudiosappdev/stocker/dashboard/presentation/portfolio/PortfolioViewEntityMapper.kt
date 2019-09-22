package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.presentation.entity.ViewEntityMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency

class PortfolioViewEntityMapper : ViewEntityMapper<Currency, PortfolioViewEntity> {

    override fun map(value: Currency): PortfolioViewEntity {
        return PortfolioViewEntity(
            value.bankName,
            value.buyPrice,
            value.buyStatus,
            value.sellPrice,
            value.sellStatus,
            value.currencyType
        )
    }
}