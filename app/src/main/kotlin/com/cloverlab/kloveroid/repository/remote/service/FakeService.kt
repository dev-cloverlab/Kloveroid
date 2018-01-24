package com.cloverlab.kloveroid.repository.remote.service

import com.cloverlab.kloveroid.repository.remote.config.FakeConfig
import com.cloverlab.kloveroid.repository.entities.FakeEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author  Jieyi Wu
 * @since   2017/11/21
 */
interface FakeService {
    @GET("${FakeConfig.API_REQUEST}/xxx/xxx")
    fun test(@QueryMap queries: Map<String, String>): Observable<FakeEntity>
}