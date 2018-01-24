package com.cloverlab.kloveroid.repository.remote

import com.cloverlab.kloveroid.internal.di.annotations.scopes.Network
import com.cloverlab.kloveroid.repository.remote.config.FakeConfig
import com.cloverlab.kloveroid.repository.remote.config.IApiConfig
import javax.inject.Inject

/**
 * Factory that creates different implementations of [IApiConfig].
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
@Network
class RestfulApiFactory @Inject constructor() {
    fun createFakeConfig(): IApiConfig = FakeConfig()
}
