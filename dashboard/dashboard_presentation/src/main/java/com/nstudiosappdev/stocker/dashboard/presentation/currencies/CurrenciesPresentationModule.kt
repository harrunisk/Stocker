package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import com.nstudiosappdev.core.preconditions.AndroidPreConditions
import com.nstudiosappdev.core.presentation.recyclerview.*
import com.nstudiosappdev.stocker.dashboard.presentation.DashboardPresentationConstants.DisplayTypes.CURRENCIES
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class CurrenciesPresentationModule {

    @Binds
    @IntoMap
    @IntKey(CURRENCIES)
    internal abstract fun provideCurrenciesViewModelFactory(factory: CurrenciesViewHolder.CurrenciesViewHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(CURRENCIES)
    internal abstract fun provideCurrenciesViewHolderFactory(binder: CurrenciesViewHolder.CurrenciesViewHolderBinder): ViewHolderBinder

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