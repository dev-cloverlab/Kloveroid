package com.cloverlab.kloveroid.api

import com.cloverlab.kloveroid.api.config.FakeConfig
import com.cloverlab.kloveroid.api.config.IApiConfig
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory that creates different implementations of [IApiConfig].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

@Singleton
class RestfulApiFactory @Inject internal constructor() {
    fun createFakeConfig(): IApiConfig {
        return FakeConfig()
    }
}
