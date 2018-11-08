package com.cloverlab.kloveroid.usecases

import com.cloverlab.kloveroid.usecases.BaseUseCase.RequestValues
import com.cloverlab.kloveroid.usecases.executor.PostExecutionThread
import com.cloverlab.kloveroid.usecases.executor.ThreadExecutor
import com.devrapid.kotlinshaver.ObserverPlugin
import com.trello.rxlifecycle3.LifecycleProvider
import com.trello.rxlifecycle3.kotlin.bindToLifecycle
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
 * By convention each UseCase implementation will return the result using a [org.reactivestreams.Subscriber]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * For passing a request parameters [RequestValues] to data layer that set a generic type for wrapping
 * vary data.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
abstract class BaseUseCase<T, R : BaseUseCase.RequestValues>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) {
    /** Provide a common parameter variable for the children class. */
    var requestValues: R? = null

    //region Usecase with an anonymous function.
    /**
     * Execute the current use case.
     *
     * @param lifecycleProvider the life cycle provider for cutting RxJava runs.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [Observer] from viewmodel, the data are omitted from database or remote.
     */
    fun <F> execute(
        lifecycleProvider: LifecycleProvider<*>? = null,
        block: Observable<T>.() -> Observable<F>,
        observer: Observer<F>
    ) =
        buildUseCaseObservable(block).apply { lifecycleProvider?.let { bindToLifecycle(it) } }.subscribe(observer)

    /**
     * Execute the current use case with request [parameter].
     *
     * @param parameter the parameter for retrieving data.
     * @param lifecycleProvider the life cycle provider for cutting RxJava runs.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [Observer] from viewmodel, the data are omitted from database or remote.
     */
    fun <F> execute(
        parameter: R,
        lifecycleProvider: LifecycleProvider<*>? = null,
        block: Observable<T>.() -> Observable<F>,
        observer: Observer<F>
    ) {
        requestValues = parameter
        execute(lifecycleProvider, block, observer)
    }

    /**
     * Execute the current use case with an anonymous function.
     *
     * @param lifecycleProvider an activity or a fragment of the [LifecycleProvider] object.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [ObserverPlugin] from viewmodel, the data are omitted from database or remote.
     */
    fun <F> execute(
        lifecycleProvider: LifecycleProvider<*>? = null,
        block: Observable<T>.() -> Observable<F>,
        observer: ObserverPlugin<F>.() -> Unit
    ) =
        execute(lifecycleProvider, block, ObserverPlugin<F>().apply(observer))

    /**
     * Execute the current use case with request [parameter] with an anonymous function..
     *
     * @param parameter the parameter for retrieving data.
     * @param lifecycleProvider an activity or a fragment of the [LifecycleProvider] object.
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @param observer a reaction of [ObserverPlugin] from viewmodel, the data are omitted from database or remote.
     */
    fun <F> execute(
        parameter: R,
        lifecycleProvider: LifecycleProvider<*>? = null,
        block: Observable<T>.() -> Observable<F>,
        observer: ObserverPlugin<F>.() -> Unit
    ) {
        requestValues = parameter
        execute(lifecycleProvider, block, observer)
    }

    /**
     * Build an [Observable] which will be used when executing the current [BaseUseCase].
     * There is a [subscribeOn] for fetching the data from the
     * [com.cloverlab.kloveroid.repository.repositories.DataRepository] works on the new thread
     * so after [subscribeOn]'s chain function will be ran on the same thread.
     * This is for who needs transfer the thread to UI, IO, or new thread again.
     *
     * @param block add some chain actions between [subscribeOn] and [observeOn].
     * @return [Observable] for connecting with a [Observer] from the kotlin layer.
     */
    private fun <F> buildUseCaseObservable(block: (Observable<T>.() -> Observable<F>)) =
        fetchUsecase()
            .subscribeOn(subscribeScheduler)
            .run { block.invoke(this) }
            .observeOn(observeScheduler)
    //endregion

    //region Usecase without an anonymous function.
    /**
     * Execute the current use case.
     *
     * @param lifecycleProvider the life cycle provider for cutting RxJava runs.
     * @param observer a reaction of [Observer] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(lifecycleProvider: LifecycleProvider<*>? = null, observer: Observer<T>) =
        buildUseCaseObservable().apply { lifecycleProvider?.let { bindToLifecycle(it) } }.subscribe(observer)

    /**
     * Execute the current use case with request [parameter].
     *
     * @param parameter the parameter for retrieving data.
     * @param lifecycleProvider the life cycle provider for cutting RxJava runs.
     * @param observer a reaction of [Observer] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(parameter: R, lifecycleProvider: LifecycleProvider<*>? = null, observer: Observer<T>) {
        requestValues = parameter
        execute(lifecycleProvider, observer)
    }

    /**
     * Execute the current use case.
     *
     * @param lifecycleProvider an activity or a fragment of the [LifecycleProvider] object.
     * @param observer a reaction of [ObserverPlugin] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(lifecycleProvider: LifecycleProvider<*>? = null, observer: ObserverPlugin<T>.() -> Unit) =
        execute(lifecycleProvider, ObserverPlugin<T>().apply(observer))

    /**
     * Execute the current use case with request [parameter].
     *
     * @param parameter the parameter for retrieving data.
     * @param lifecycleProvider an activity or a fragment of the [LifecycleProvider] object.
     * @param observer a reaction of [ObserverPlugin] from viewmodel, the data are omitted from database or remote.
     */
    fun execute(parameter: R, lifecycleProvider: LifecycleProvider<*>? = null, observer: ObserverPlugin<T>.() -> Unit) {
        requestValues = parameter
        execute(lifecycleProvider, observer)
    }

    /**
     * Build an [Observable] which will be used when executing the current [BaseUseCase] and run on
     * the UI thread.
     *
     * @return [Observable] for connecting with a [Observer] from the kotlin layer.
     */
    private fun buildUseCaseObservable() =
        fetchUsecase()
            .subscribeOn(subscribeScheduler)
            .observeOn(observeScheduler)
    //endregion

    /**
     * Choose a method from [com.cloverlab.kloveroid.repository.source.IDataStore] and fit this usecase
     * for return some data.
     *
     * @return an [Observer] for chaining on working threads.
     */
    protected abstract fun fetchUsecase(): Observable<T>

    /**
     * Obtain a thread for while [Observable] is doing their tasks.
     *
     * @return [Scheduler] implement from [PostExecutionThread].
     */
    protected open val observeScheduler: Scheduler = postExecutionThread.scheduler

    /**
     * Obtain a thread from [ThreadPoolExecutor] for while [Scheduler] is doing their tasks.
     *
     * @return [Scheduler] implement from [ThreadExecutor].
     */
    protected open val subscribeScheduler: Scheduler = Schedulers.from(threadExecutor)

    /** Interface for wrap a data for passing to a request.*/
    interface RequestValues
}
