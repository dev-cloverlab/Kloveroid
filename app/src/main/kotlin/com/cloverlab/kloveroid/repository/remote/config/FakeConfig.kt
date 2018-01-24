package com.cloverlab.kloveroid.repository.remote.config

/**
 * The remote uri configuration.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class FakeConfig : IApiConfig {
    companion object {
        const val API_REQUEST = "xxx/xx/xxx"
        // All basic http api url of domain url.
        private val BASE_URL = "http://xxx.xxx.xx/"
    }

    override val apiBaseUrl: String = BASE_URL
}
