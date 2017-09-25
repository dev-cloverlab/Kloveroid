package com.cloverlab.kloveroid.data.source

import com.cloverlab.kloveroid.data.entities.FakeEntity
import com.cloverlab.kloveroid.mvp.models.FakeModel
import rx.Observable

/**
 * Interface that represents a data store from where data is retrieved.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

interface IDataStore {
    /**
     * Get an [rx.Observable] which will emit a List of [FakeEntity].
     */
    fun createEntity(model: FakeModel): Observable<FakeEntity>
}
