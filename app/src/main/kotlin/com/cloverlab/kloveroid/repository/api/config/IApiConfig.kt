package com.cloverlab.kloveroid.repository.api.config

/**
 * Interface of the setting of the difference http configurations.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface IApiConfig {
    /**
     * Obtain the base http url.
     *
     * @return restful api base url information.
     */
    val apiBaseUrl: String
}
