package com.cloverlab.kloveroid.internal.di.components

import com.cloverlab.kloveroid.App
import com.cloverlab.kloveroid.data.source.CloudDataStore
import com.cloverlab.kloveroid.internal.di.annotations.scopes.Network
import com.cloverlab.kloveroid.internal.di.modules.NetModule
import dagger.Component

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Network
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(NetModule::class))
interface NetComponent {
    object Initializer {
        fun init(): NetComponent = DaggerNetComponent.builder()
            .appComponent(App.appComponent)
            .netModule(NetModule())
            .build()
    }

    fun inject(cloudDataStore: CloudDataStore)
}