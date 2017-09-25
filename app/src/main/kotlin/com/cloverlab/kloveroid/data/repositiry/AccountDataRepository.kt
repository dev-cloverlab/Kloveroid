package com.cloverlab.kloveroid.data.repositiry

import com.cloverlab.kloveroid.data.mapper.FakeEntityMapper
import com.cloverlab.kloveroid.data.source.factory.DataStoreFactory
import com.cloverlab.kloveroid.domain.repository.IAccountRepository
import com.cloverlab.kloveroid.mvp.models.FakeModel
import dagger.internal.Preconditions
import rx.Observable
import javax.inject.Inject

/**
 * Low layer pure entity convert to kotlin layer data model from the repositories.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

class AccountDataRepository @Inject
internal constructor(private val dataStoreFactory: DataStoreFactory): IAccountRepository {
    @Inject private lateinit var fakeMapper: FakeEntityMapper

    override fun CreateFakes(fakeModel: FakeModel): Observable<FakeModel> {
        Preconditions.checkNotNull(fakeModel)

        return dataStoreFactory.createCloud()
            .createEntity(fakeModel)
            .map { entity -> fakeMapper.transformTo(entity) }
    }
}
