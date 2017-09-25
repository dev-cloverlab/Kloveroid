package com.cloverlab.kloveroid.domain

import com.cloverlab.kloveroid.domain.executor.PostExecutionThread
import com.cloverlab.kloveroid.domain.executor.ThreadExecutor
import com.cloverlab.kloveroid.domain.repository.IAccountRepository
import com.cloverlab.kloveroid.mvp.models.FakeModel
import rx.Observable
import rx.Subscriber
import rx.Subscription
import javax.inject.Inject


/**
 * This class is an implementation of [BaseUseCase] that represents a use case for an example.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

class CreateFakeUseCase @Inject
internal constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread,
                     private val accountRepository: IAccountRepository): BaseUseCase<CreateFakeUseCase.Requests>(
    threadExecutor,
    postExecutionThread) {

    /**
     * Executes the current use case with request parameters.
     *
     * @param request           Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     */
    override fun execute(request: Requests, useCaseSubscriber: Subscriber<*>) {
        this.setRequestValues(request)

        super.execute(request, useCaseSubscriber)
    }

    /**
     * Builds an [Observable] which will be used when executing the current [BaseUseCase].
     *
     * @return [Observable] for connecting with a [Subscription] from the kotlin layer.
     */
    override fun buildUseCaseObservable(): Observable<*> {
        return accountRepository.CreateFakes(this.getRequestValues().fakeModel)
    }

    /**
     * Wrapping data requests for general situation.
     */
    class Requests(private val fakeModel: FakeModel): BaseUseCase.RequestValues()
}
