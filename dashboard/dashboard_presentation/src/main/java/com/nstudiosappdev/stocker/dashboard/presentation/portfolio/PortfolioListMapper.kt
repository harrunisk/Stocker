package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.presentation.entity.ViewEntityMapper
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency

class PortfolioListMapper(
    private val portfolioViewEntityMapper: ViewEntityMapper<Currency, PortfolioViewEntity>
) : DisplayItemListMapper<Currency>{

    override fun map(items: List<Currency>): List<DisplayItem> {
        val mappedItems = arrayListOf<DisplayItem>()
        for ( item in items) {
            mappedItems.add(portfolioViewEntityMapper.map(item))
        }
        return mappedItems
    }
}