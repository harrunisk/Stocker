package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import androidx.lifecycle.ViewModel
import com.nstudiosappdev.core.presentation.entity.ViewEntityMapper
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.core.presentation.viewmodel.ViewModelKey
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class CurrenciesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrenciesViewModel::class)
    abstract fun bindCurrenciesViewModel(currenciesViewModel: CurrenciesViewModel): ViewModel

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