package com.cloverlab.kloveroid.usecases

import com.cloverlab.kloveroid.repository.entities.FakeEntity
import com.cloverlab.kloveroid.repository.repositories.IDataStore
import com.cloverlab.kloveroid.usecases.executor.PostExecutionThread
import com.cloverlab.kloveroid.usecases.executor.ThreadExecutor
import io.reactivex.Observable


/**
 * This class is an implementation of [BaseUseCase] that represents a use case for an example.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
class CreateFakeUseCase constructor(threadExecutor: ThreadExecutor,
                                    postExecutionThread: PostExecutionThread,
                                    private val repository: IDataStore) :
    BaseUseCase<FakeEntity, CreateFakeUseCase.Requests>(threadExecutor, postExecutionThread) {
    override fun fetchUsecase(): Observable<FakeEntity> = repository.createEntity(requestValues?.fakeEntity
                                                                                  ?: FakeEntity("", 33333, ""))

    /** Wrapping data requests for general situation.*/
    class Requests(val fakeEntity: FakeEntity) : BaseUseCase.RequestValues
}
