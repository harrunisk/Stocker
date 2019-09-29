package com.nstudiosappdev.stocker.dashboard.presentation

import androidx.lifecycle.ViewModel
import com.nstudiosappdev.core.presentation.entity.ViewEntityMapper
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.core.presentation.viewmodel.ViewModelKey
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import com.nstudiosappdev.stocker.dashboard.presentation.liveCurrencies.LiveCurrenciesViewModel
import com.nstudiosappdev.stocker.dashboard.presentation.portfolio.PortfolioViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class CurrenciesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LiveCurrenciesViewModel::class)
    abstract fun bindCurrenciesViewModel(liveCurrenciesViewModel: LiveCurrenciesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PortfolioViewModel::class)
    abstract fun bindPortfolioViewModel(portfolioViewModel: PortfolioViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideCurrenciesViewEntityMapper(): ViewEntityMapper<Currency, CurrenciesViewEntity> =
            CurrenciesViewEntityMapper()

        @JvmStatic
        @Provides
        fun provideCurrenciesDisplayListMapper(viewEntityMapper: ViewEntityMapper<Currency, CurrenciesViewEntity>): DisplayItemListMapper<Currency> =
            CurrenciesListMapper(
                viewEntityMapper
            )
    }
}