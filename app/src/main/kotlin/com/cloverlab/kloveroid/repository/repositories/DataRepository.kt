package com.cloverlab.kloveroid.repository.repositories

import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Local
import com.cloverlab.kloveroid.internal.di.annotations.qualifiers.Remote
import com.cloverlab.kloveroid.repository.entities.FakeEntity
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Choose a good strategy depend on the keeping time in the cache for fetching the data from
 * 'Local cache', 'Remote Server', 'Local Database'.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
@Singleton
class DataRepository @Inject constructor(@Local private var local: IDataStore,
                                         @Remote private var remote: IDataStore) :
    IDataStore {
    override fun createEntity(fakeEntity: FakeEntity): Observable<FakeEntity> = remote.createEntity(fakeEntity)
}