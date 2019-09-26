package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.preconditions.AndroidPreConditions
import com.nstudiosappdev.core.presentation.recyclerview.*
import com.nstudiosappdev.stocker.dashboard.presentation.DashboardPresentationConstants.DisplayTypes.PORTFOLIO
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class PortfolioPresentationModule {

    @Binds
    @IntoMap
    @IntKey(PORTFOLIO)
    internal abstract fun providePortfolioViewModelFactory(factory: PortfolioViewHolder.PortfolioViewHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(PORTFOLIO)
    internal abstract fun providePortfolioViewHolderFactory(binder: PortfolioViewHolder.PortfolioViewHolderBinder): ViewHolderBinder

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideDisplayItemComparator(): DisplayItemComparator = DefaultDisplayItemComparator()

        @JvmStatic
        @Provides
        fun provideRecyclerViewClickListener(): RecyclerViewClickListener = CurrenciesFragment()

        @JvmStatic
        @Provides
        fun provideRecyclerAdapter(
            itemComparator: DisplayItemComparator,
            factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
            binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder>,
            androidPreConditions: AndroidPreConditions,
            recyclerViewClickListener: RecyclerViewClickListener
        ): RecyclerViewAdapter {
            return RecyclerViewAdapter(
                itemComparator = itemComparator,
                viewHolderFactoryMap = factoryMap,
                viewBinderFactoryMap = binderMap,
                androidPreconditions = androidPreConditions,
                recyclerViewClickListener = recyclerViewClickListener
            )
        }

    }
}