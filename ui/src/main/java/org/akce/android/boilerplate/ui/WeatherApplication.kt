package org.akce.android.boilerplate.ui

import android.app.Application
import org.akce.android.boilerplate.cache.BuildConfig
import org.akce.android.boilerplate.ui.di.applicationModule
import org.akce.android.boilerplate.ui.di.browseModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(applicationModule, browseModule))
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
