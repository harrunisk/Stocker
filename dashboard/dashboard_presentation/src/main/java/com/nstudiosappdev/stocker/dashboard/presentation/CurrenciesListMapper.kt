package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.presentation.entity.ViewEntityMapper
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.stocker.dashboard.domain.Currency

class CurrenciesListMapper(
    private val currenciesViewEntityMapper: ViewEntityMapper<Currency, CurrenciesViewEntity>
) : DisplayItemListMapper<Currency> {

    override fun map(items: List<Currency>): List<DisplayItem> {
        val mappedItems = arrayListOf<DisplayItem>()
        for ( item in items) {
            mappedItems.add(currenciesViewEntityMapper.map(item))
        }

        return mappedItems
    }
}