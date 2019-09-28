package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.presentation.entity.ViewEntity
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.stocker.dashboard.presentation.DashboardPresentationConstants

class PortfolioViewEntity(
    val bankName: String?,
    val buyPrice: String?,
    val buyStatus: String?,
    val sellPrice: String?,
    val sellStatus: String?,
    val currencyType: String?
) : ViewEntity, DisplayItem {

    override fun type(): Int =
        DashboardPresentationConstants.TYPES.EURO
}