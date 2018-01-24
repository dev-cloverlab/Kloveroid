package com.cloverlab.kloveroid.internal.di.components

import com.cloverlab.kloveroid.internal.di.annotations.scopes.LocalData
import com.cloverlab.kloveroid.internal.di.modules.DatabaseModule
import com.cloverlab.kloveroid.repository.repositories.source.LocalDataStore
import dagger.Component

/**
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
@LocalData
@Component(dependencies = [AppComponent::class], modules = [DatabaseModule::class])
interface DatabaseComponent {
    object Initializer {
//        fun init(): DatabaseComponent = DaggerDatabaseComponent.builder()
//            .appComponent(App.appComponent)
//            .roomModule(DatabaseModule())
//            .build()
    }

    fun inject(localDataStore: LocalDataStore)
}