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
    BaseUseCase<CreateFakeUseCase.Requests>(threadExecutor, postExecutionThread) {
    /**
     * Executes the current use case with request parameters.
     *
     * @param request           Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     */
    override fun execute(request: Requests, useCaseSubscriber: io.reactivex.Observable<*>) {
        requestValues = request

        super.execute(request, useCaseSubscriber)
    }

    /**
     * Builds an [Observable] which will be used when executing the current [BaseUseCase].
     *
     * @return [Observable] for connecting with a [Subscription] from the kotlin layer.
     */
    override fun buildUseCaseObservable(): Observable<*> {
        return repository.createEntity(requestValues.fakeModel)
    }

    /**
     * Wrapping data requests for general situation.
     */
    class Requests(val fakeModel: FakeModel): BaseUseCase.RequestValues()
}