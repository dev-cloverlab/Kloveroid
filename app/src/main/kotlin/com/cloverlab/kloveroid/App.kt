package com.cloverlab.kloveroid

import com.cloverlab.kloveroid.internal.di.components.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Android Main Application
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
class App : DaggerApplication() {
    companion object {
        lateinit var injector: AndroidInjector<App>
        val appComponent by lazy { injector as AppComponent }
    }

    init {
        // Create an application component injector.
        injector = DaggerAppComponent.builder().create(this)
    }

    override fun applicationInjector(): AndroidInjector<App> = injector
}