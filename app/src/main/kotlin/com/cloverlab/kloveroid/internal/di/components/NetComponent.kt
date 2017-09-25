package com.cloverlab.kloveroid.internal.di.components

import com.cloverlab.kloveroid.data.source.CloudDataStore
import com.cloverlab.kloveroid.internal.di.modules.NetModule
import dagger.Component
import javax.inject.Singleton

/**
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

@Singleton
@Component(modules = arrayOf(NetModule::class))
interface NetComponent {
    object Initializer {
        fun init(): NetComponent = DaggerNetComponent.builder()
            .netModule(NetModule())
            .build()
    }

    fun inject(cloudDataStore: CloudDataStore)
}
