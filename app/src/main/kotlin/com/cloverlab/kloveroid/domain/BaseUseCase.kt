package com.cloverlab.kloveroid.domain

import com.cloverlab.kloveroid.domain.BaseUseCase.RequestValues
import com.cloverlab.kloveroid.domain.executor.PostExecutionThread
import com.cloverlab.kloveroid.domain.executor.ThreadExecutor
import com.cloverlab.kloveroid.utilies.AppLog
import com.trello.rxlifecycle.android.ActivityEvent
import com.trello.rxlifecycle.android.FragmentEvent
import com.trello.rxlifecycle.android.RxLifecycleAndroid
import dagger.internal.Preconditions
import rx.Observable
import rx.Scheduler
import rx.Subscriber
import rx.Subscription
import rx.schedulers.Schedulers
import java.util.concurrent.ThreadPoolExecutor

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case in the
 * application should implement this contract).
 *
 *
 * By convention each UseCase implementation will return the result using a [rx.Subscriber] that will
 * execute its job in a background thread and will post the result in the UI thread.
 *
 *
 * For passing a request parameters [RequestValues] to data layer that set a generic type for wrapping
 * vary data.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

abstract class BaseUseCase<R: BaseUseCase.RequestValues> internal constructor(private val threadExecutor: ThreadExecutor,
                                                                              private val postExecutionThread: PostExecutionThread) {
    internal var requestValues: R? = null

    /**
     * Obtain a thread for while [Observable] is doing their tasks.
     *
     * @return [Scheduler] implement from [PostExecutionThread].
     */
    protected val observeScheduler: Scheduler
        get() = postExecutionThread.scheduler

    /**
     * Obtain a thread from [ThreadPoolExecutor] for while [Scheduler] is doing their tasks.
     *
     * @return [Scheduler] implement from [ThreadExecutor].
     */
    protected val subscribeScheduler: Scheduler
        get() = Schedulers.from(threadExecutor)

    /**
     * Builds an [Observable] which will be used when executing the current [BaseUseCase].
     *
     * @return [Observable] for connecting with a [Subscription] from the kotlin layer.
     */
    protected abstract fun buildUseCaseObservable(): Observable<*>

    /**
     * Executes the current use case with request parameters.
     *
     * @param request           Send the data to data layer with request parameters.
     * @param useCaseSubscriber The guy who will be listen to the observable build with
     * [.buildUseCaseObservable].
     */
    open fun execute(request: R, useCaseSubscriber: Subscriber<*>) {
        Preconditions.checkNotNull(request)
        Preconditions.checkNotNull(useCaseSubscriber)

        var observable: Observable<*> = this.buildUseCaseObservable()
            .doOnUnsubscribe { AppLog.d("Unsubscribing subscription") }

        // Assign the one of them to RxJava request.
        if (null != request.fragmentLifecycle) {
            observable = observable.compose(RxLifecycleAndroid.bindFragment(request.fragmentLifecycle!!))
        }
        else if (null != request.activityLifecycle) {
            observable = observable.compose(RxLifecycleAndroid.bindActivity(request.activityLifecycle!!))
        }

        observable.subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
            .subscribe(useCaseSubscriber)
    }

    /**
     * Interface for wrap a data for passing to a request.
     */
    abstract class RequestValues {
        var fragmentLifecycle: Observable<FragmentEvent>? = null
        var activityLifecycle: Observable<ActivityEvent>? = null
    }
}
