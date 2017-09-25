package com.cloverlab.kloveroid.data.source

import com.cloverlab.kloveroid.data.entities.FakeEntity
import com.cloverlab.kloveroid.mvp.models.FakeModel
import rx.Observable

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */

class LocalDataStore: IDataStore {
    override fun createEntity(model: FakeModel): Observable<FakeEntity> {
        TODO("not yet")
    }
}
