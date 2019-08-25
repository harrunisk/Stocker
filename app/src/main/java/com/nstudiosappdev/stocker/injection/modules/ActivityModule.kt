package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.core.injection.scope.ActivityScope
import com.nstudiosappdev.stocker.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeMainActivityInjector(): MainActivity
}