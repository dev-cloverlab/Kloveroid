package com.cloverlab.kloveroid.repository.api.config

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
class FakeConfig : IApiConfig {
    companion object {
        const val API_REQUEST = "xxx/xx/xxx"
        // All basic http api url of domain url.
        private val BASE_URL = "http://xxx.xxx.xx/"
    }

    override val apiBaseUrl: String = BASE_URL
}
