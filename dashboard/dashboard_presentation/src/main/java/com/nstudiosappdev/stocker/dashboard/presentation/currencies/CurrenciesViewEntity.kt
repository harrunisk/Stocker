package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import com.nstudiosappdev.core.presentation.entity.ViewEntity
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem

class CurrenciesViewEntity(
    val bankName: String?,
    val buyPrice: String?,
    val buyStatus: String?,
    val sellPrice: String?,
    val sellStatus: String?
) : ViewEntity, DisplayItem{

    override fun type(): Int =
        CurrenciesPresentationConstants.TYPES.USD
}
