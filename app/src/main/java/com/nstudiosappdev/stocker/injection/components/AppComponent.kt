package com.nstudiosappdev.stocker.injection.components

import com.nstudiosappdev.stocker.StockerApp
import com.nstudiosappdev.stocker.injection.modules.*
import com.nstudiosappdev.stocker.injection.modules.ActivityModule
import com.nstudiosappdev.stocker.injection.modules.DataModule
import com.nstudiosappdev.stocker.injection.modules.DomainModule
import com.nstudiosappdev.stocker.injection.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        DomainModule::class,
        DataModule::class,
        ViewModelModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: StockerApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: StockerApp)
}