package com.cloverlab.kloveroid.repository.api.service

import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.repository.api.config.FakeConfig
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author  jieyi
 * @since   11/21/17
 */
interface FakeService {
    @GET("${FakeConfig.API_REQUEST}/xxx/xxx")
    fun test(@QueryMap queries: Map<String, String>): Observable<FakeModel>
}