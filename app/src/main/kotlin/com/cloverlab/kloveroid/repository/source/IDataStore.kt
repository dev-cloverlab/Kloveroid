package com.cloverlab.kloveroid.repository.source

import com.cloverlab.kloveroid.mvp.models.FakeModel
import io.reactivex.Observable

/**
 * Interface that represents a data store from where data is retrieved.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface IDataStore {
    /**
     * Get an [rx.Observable] which will emit a List of [FakeModel].
     */
    fun createEntity(model: FakeModel): Observable<FakeModel>
}
