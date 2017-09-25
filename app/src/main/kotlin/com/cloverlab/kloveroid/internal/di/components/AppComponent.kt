package com.cloverlab.kloveroid.internal.di.components

import android.content.Context
import com.cloverlab.kloveroid.App
import com.cloverlab.kloveroid.internal.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,
    AndroidSupportInjectionModule::class))
interface AppComponent: AndroidInjector<App> {
    /** [AndroidInjector] Builder for using on this whole app. */
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>()

    /** Providing to dependence components. */
    fun context(): Context

//    fun threadExecutor(): ThreadExecutor
//    fun postExecutionThread(): PostExecutionThread
//    fun accountRepository(): IAccountRepository
}