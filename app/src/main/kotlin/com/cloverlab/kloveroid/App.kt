package com.cloverlab.kloveroid

import android.app.Application
import android.content.Context
import com.cloverlab.kloveroid.internal.di.components.AppComponent

/**
 * Android Main Application
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

class App: Application() {
    companion object {
        @JvmStatic
        fun appComponent(context: Context): AppComponent =
            (context as App).appComponent
    }

    private val appComponent: AppComponent by lazy { AppComponent.Initializer.init(this) }
}