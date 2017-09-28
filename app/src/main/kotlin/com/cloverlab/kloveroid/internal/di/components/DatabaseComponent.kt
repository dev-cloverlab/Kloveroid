package com.cloverlab.kloveroid.internal.di.components

import com.cloverlab.kloveroid.internal.di.annotations.scopes.LocalData
import com.cloverlab.kloveroid.internal.di.modules.DatabaseModule
import com.cloverlab.kloveroid.repository.source.LocalDataStore
import dagger.Component

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
@LocalData
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(DatabaseModule::class))
interface DatabaseComponent {
    object Initializer {
//        fun init(): DatabaseComponent = DaggerDatabaseComponent.builder()
//            .appComponent(App.appComponent)
//            .roomModule(DatabaseModule())
//            .build()
    }

    fun inject(localDataStore: LocalDataStore)
}