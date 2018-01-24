package com.cloverlab.kloveroid.repository.repositories

import com.cloverlab.kloveroid.entities.FakeEntity
import io.reactivex.Observable

/**
 * Interface that represents a data store from where data is retrieved.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
interface IDataStore {
    /**
     * Get an [Observable] which will emit a List of [FakeEntity].
     */
    fun createEntity(entity: FakeEntity): Observable<FakeEntity>
}
