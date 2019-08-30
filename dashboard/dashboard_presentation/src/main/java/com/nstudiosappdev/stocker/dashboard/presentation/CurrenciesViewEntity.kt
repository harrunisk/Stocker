package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.presentation.entity.ViewEntity
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.stocker.dashboard.domain.CurrencyStatus

class CurrenciesViewEntity(
    val bankName: String?,
    val buyPrice: String?,
    val buyStatus: CurrencyStatus?,
    val sellPrice: String?,
    val sellStatus: CurrencyStatus?
) : ViewEntity, DisplayItem{

    override fun type(): Int = CurrenciesPresentationConstants.TYPES.USD
}
