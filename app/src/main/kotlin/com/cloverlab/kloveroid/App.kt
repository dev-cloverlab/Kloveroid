package com.cloverlab.kloveroid

import com.cloverlab.kloveroid.internal.di.components.AppComponent
import com.cloverlab.kloveroid.internal.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Android Main Application
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
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
