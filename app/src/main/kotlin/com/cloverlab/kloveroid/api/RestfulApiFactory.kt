package com.cloverlab.kloveroid.api

import com.cloverlab.kloveroid.api.config.FakeConfig
import com.cloverlab.kloveroid.api.config.IApiConfig
import com.cloverlab.kloveroid.internal.di.annotations.scopes.Network
import javax.inject.Inject

/**
 * Factory that creates different implementations of [IApiConfig].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Network
class RestfulApiFactory @Inject constructor() {
    fun createFakeConfig(): IApiConfig {
        return FakeConfig()
    }
}
