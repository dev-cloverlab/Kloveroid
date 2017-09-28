package com.cloverlab.kloveroid.repository.source

import com.cloverlab.kloveroid.mvp.models.FakeModel

/**
 * @author Jieyi Wu
 * @since 09/25/17
 */
class LocalDataStore: IDataStore {
    override fun createEntity(model: FakeModel): Observable<FakeModel> {
        TODO("not yet")
    }
}
