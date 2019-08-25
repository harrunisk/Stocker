package com.nstudiosappdev.stocker.injection.modules

import android.content.Context
import com.nstudiosappdev.stocker.StockerApp
import dagger.Provides

class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: StockerApp): Context {
        return app.applicationContext
    }
}