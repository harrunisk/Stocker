package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

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
abstract class PortfolioViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PortfolioViewModel::class)
    abstract fun bindPortfolioViewModel(portfolioViewModel: PortfolioViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providePortfolioViewEntityMapper(): ViewEntityMapper<Currency, PortfolioViewEntity> =
            PortfolioViewEntityMapper()

        @JvmStatic
        @Provides
        @Named("PORTFOLIO")
        fun providePortfolioDisplayListMapper(viewEntityMapper: ViewEntityMapper<Currency, PortfolioViewEntity>): DisplayItemListMapper<Currency> =
            PortfolioListMapper(
                viewEntityMapper
            )
    }
}