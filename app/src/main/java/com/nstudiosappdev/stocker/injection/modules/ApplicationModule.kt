package com.nstudiosappdev.stocker.injection.modules

import android.content.Context
import com.nstudiosappdev.core.injection.modules.CoreModule
import com.nstudiosappdev.core.presentation.factory.DefaultIntentFactory
import com.nstudiosappdev.core.presentation.factory.IntentFactory
import com.nstudiosappdev.stocker.StockerApp
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference
import javax.inject.Singleton
@Module(
    includes = [CoreModule::class]
)
class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: StockerApp): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideIntentFactory(context: Context): IntentFactory = DefaultIntentFactory(WeakReference(context))
}