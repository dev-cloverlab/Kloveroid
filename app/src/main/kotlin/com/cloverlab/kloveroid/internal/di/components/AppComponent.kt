package com.cloverlab.kloveroid.internal.di.components

import android.content.Context
import com.cloverlab.kloveroid.App
import com.cloverlab.kloveroid.domain.executor.PostExecutionThread
import com.cloverlab.kloveroid.domain.executor.ThreadExecutor
import com.cloverlab.kloveroid.domain.repository.IAccountRepository
import com.cloverlab.kloveroid.internal.di.modules.AppModule
import com.cloverlab.kloveroid.internal.di.modules.NetModule
import com.cloverlab.kloveroid.ui.BaseActivity
import dagger.Component
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {
    object Initializer {
        fun init(app: App): AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .netModule(NetModule())
            .build()
    }

    fun inject(baseActivity: BaseActivity)

    // Exposed to sub-graphs.
    fun context(): Context

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    fun accountRepository(): IAccountRepository
}