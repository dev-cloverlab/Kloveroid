package com.cloverlab.kloveroid.repository.api

import com.cloverlab.kloveroid.internal.di.annotations.scopes.Network
import com.cloverlab.kloveroid.repository.api.config.FakeConfig
import com.cloverlab.kloveroid.repository.api.config.IApiConfig
import javax.inject.Inject

/**
 * Factory that creates different implementations of [IApiConfig].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Network
class RestfulApiFactory @Inject constructor() {
    fun createFakeConfig(): IApiConfig = FakeConfig()
}
