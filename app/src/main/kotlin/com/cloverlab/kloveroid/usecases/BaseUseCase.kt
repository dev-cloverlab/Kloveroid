package com.cloverlab.kloveroid.usecases

import com.cloverlab.kloveroid.usecases.BaseUseCase.RequestValues
import com.cloverlab.kloveroid.usecases.executor.PostExecutionThread
import com.cloverlab.kloveroid.usecases.executor.ThreadExecutor
import com.devrapid.kotlinknifer.ObserverPlugin
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
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
abstract class BaseUseCase<T, R : BaseUseCase.RequestValues>(threadExecutor: ThreadExecutor,
                                                             postExecutionThread: PostExecutionThread) {
    /** Provide a common parameter variable for the children class. */
    var requestValues: R? = null

    /**
     * Executes the current use case.
     *
     * @param lifecycleProvider the life cycle provider for cutting RxJava runs.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [Observer] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(lifecycleProvider: LifecycleProvider<*>? = null,
                block: (Observable<T>.() -> Observable<T>)? = null,
                observer: Observer<T>) =
        buildUseCaseObservable(block).apply { lifecycleProvider?.let { bindToLifecycle(it) } }.subscribe(observer)

    /**
     * Executes the current use case with request parameters.
     *
     * @param parameter the parameter for retrieving data.
     * @param lifecycleProvider the life cycle provider for cutting RxJava runs.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [Observer] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(parameter: R,
                lifecycleProvider: LifecycleProvider<*>? = null,
                block: (Observable<T>.() -> Observable<T>)? = null,
                observer: Observer<T>) {
        requestValues = parameter
        execute(lifecycleProvider, block, observer)
    }

    /**
     * Executes the current use case with an anonymous function.
     *
     * @param lifecycleProvider an activity or a fragment of the [LifecycleProvider] object.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [ObserverPlugin] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(lifecycleProvider: LifecycleProvider<*>? = null,
                block: (Observable<T>.() -> Observable<T>)? = null,
                observer: ObserverPlugin<T>.() -> Unit) =
        execute(lifecycleProvider, block, ObserverPlugin<T>().apply(observer))

    /**
     * Executes the current use case with request parameters with an anonymous function..
     *
     * @param parameter the parameter for retrieving data.
     * @param lifecycleProvider an activity or a fragment of the [LifecycleProvider] object.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [ObserverPlugin] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(parameter: R,
                lifecycleProvider: LifecycleProvider<*>? = null,
                block: (Observable<T>.() -> Observable<T>)? = null,
                observer: ObserverPlugin<T>.() -> Unit) {
        requestValues = parameter
        execute(lifecycleProvider, block, observer)
    }

    /**
     * Choose a method from [IDataStore] and fit this usecase for return some data.
     *
     * @return an [Observer] for chaining on working threads.
     */
    abstract protected fun fetchUsecase(): Observable<T>

    /**
     * Obtain a thread for while [Observable] is doing their tasks.
     *
     * @return [Scheduler] implement from [PostExecutionThread].
     */
    open protected val observeScheduler: Scheduler = postExecutionThread.scheduler

    /**
     * Obtain a thread from [ThreadPoolExecutor] for while [Scheduler] is doing their tasks.
     *
     * @return [Scheduler] implement from [ThreadExecutor].
     */
    open protected val subscribeScheduler: Scheduler = Schedulers.from(threadExecutor)

    /**
     * Builds an [Observable] which will be used when executing the current [BaseUsecase].
     *
     * @return [Observable] for connecting with a [Observer] from the kotlin layer.
     */
    private fun buildUseCaseObservable(block: (Observable<T>.() -> Observable<T>)? = null) = fetchUsecase().
        subscribeOn(subscribeScheduler).
        apply { block?.invoke(this) }.
        observeOn(observeScheduler)

    /** Interface for wrap a data for passing to a request.*/
    interface RequestValues
}