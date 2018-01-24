package com.cloverlab.kloveroid.repository.repositories.source

import com.cloverlab.kloveroid.entities.FakeEntity
import com.cloverlab.kloveroid.repository.repositories.IDataStore
import io.reactivex.Observable

/**
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class LocalDataStore : IDataStore {
    override fun createEntity(entity: FakeEntity): Observable<FakeEntity> {
        TODO("not yet")
    }
}
