package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import com.nstudiosappdev.core.preconditions.AndroidPreConditions
import com.nstudiosappdev.core.presentation.recyclerview.*
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesPresentationConstants.TYPES.USD
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

@Module
abstract class CurrenciesPresentationModule {

    @Binds
    @IntoMap
    @IntKey(USD)
    internal abstract fun provideCurrenciesViewModelFactory(factory: CurrenciesViewHolder.CurrenciesViewHolderFactory): ViewHolderFactory

    @Binds
    @IntoMap
    @IntKey(USD)
    internal abstract fun provideCurrenciesViewHolderFactory(binder: CurrenciesViewHolder.CurrenciesViewHolderBinder): ViewHolderBinder

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideDisplayItemComperator(): DisplayItemComperator = DefaultDisplayItemComperator()

        @JvmStatic
        @Provides
        fun provideRecyclerAdapter(
            itemComperator: DisplayItemComperator,
            factoryMap: Map<Int, @JvmSuppressWildcards ViewHolderFactory>,
            binderMap: Map<Int, @JvmSuppressWildcards ViewHolderBinder>,
            androidPreConditions: AndroidPreConditions
        ): RecyclerViewAdapter {
            return RecyclerViewAdapter(
                itemComperator = itemComperator,
                viewHolderFactoryMap = factoryMap,
                viewBinderFactoryMap = binderMap,
                androidPreconditions = androidPreConditions
            )
        }
    }
}