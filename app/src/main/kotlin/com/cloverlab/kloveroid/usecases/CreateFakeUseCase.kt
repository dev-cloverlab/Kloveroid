package com.cloverlab.kloveroid.usecases

import com.cloverlab.kloveroid.mvp.models.FakeModel
import com.cloverlab.kloveroid.repository.source.IDataStore
import com.cloverlab.kloveroid.usecases.executor.PostExecutionThread
import com.cloverlab.kloveroid.usecases.executor.ThreadExecutor
import io.reactivex.Observable


/**
 * This class is an implementation of [BaseUseCase] that represents a use case for an example.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
class CreateFakeUseCase constructor(threadExecutor: ThreadExecutor,
                                    postExecutionThread: PostExecutionThread,
                                    private val repository: IDataStore):
    BaseUseCase<FakeModel, CreateFakeUseCase.Requests>(threadExecutor, postExecutionThread) {
    override fun fetchUsecase(): Observable<FakeModel> = repository.createEntity(requestValues?.fakeModel ?:
        FakeModel("", 33333, ""))

    /** Wrapping data requests for general situation.*/
    class Requests(val fakeModel: FakeModel): BaseUseCase.RequestValues
}
