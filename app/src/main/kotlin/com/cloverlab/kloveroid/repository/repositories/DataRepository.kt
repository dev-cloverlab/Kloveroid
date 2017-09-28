package com.cloverlab.kloveroid.repository.repositories

import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Local
import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Remote
import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.repository.source.IDataStore
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author  jieyi
 * @since   9/25/17
 */
@Singleton
class DataRepository @Inject constructor(@Local private var local: IDataStore,
                                         @Remote private var remote: IDataStore): IDataStore {
    override fun createEntity(fakeModel: FakeModel): Observable<FakeModel> {
        return remote.createEntity(fakeModel)
    }
}