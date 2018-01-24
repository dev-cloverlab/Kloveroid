package com.cloverlab.kloveroid.repository.remote.config

/**
 * Interface of the setting of the difference http configurations.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
interface IApiConfig {
    /**
     * Obtain the base http url.
     *
     * @return restful api base url information.
     */
    val apiBaseUrl: String
}
