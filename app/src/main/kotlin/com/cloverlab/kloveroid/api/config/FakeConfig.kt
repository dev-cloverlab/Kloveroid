package com.cloverlab.kloveroid.api.config

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */

class FakeConfig: IApiConfig {
    override val apiBaseUrl: String = BASE_URL

    companion object {
        private val BASE_URL = "http://xxx.xxx.xx"
    }
}
