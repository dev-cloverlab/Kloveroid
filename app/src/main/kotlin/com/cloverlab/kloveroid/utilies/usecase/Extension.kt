package com.cloverlab.kloveroid.utilies.usecase

import com.cloverlab.kloveroid.usecases.BaseUseCase
import com.devrapid.kotlinshaver.ObserverPlugin
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable

/**
 * @author  Jieyi Wu
 * @since   2018/01/24
 */
fun <T, F, V : BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(
    usecase: BaseUseCase<T, V>,
    parameter: V,
    block: Observable<T>.() -> Observable<F>,
    observer: ObserverPlugin<F>.() -> Unit
) =
    usecase.execute(parameter, this, block, observer)

fun <T, F, V : BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(
    usecase: BaseUseCase<T, V>,
    block: Observable<T>.() -> Observable<F>,
    observer: ObserverPlugin<F>.() -> Unit
) =
    usecase.execute(this, block, observer)

fun <T, V : BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(
    usecase: BaseUseCase<T, V>,
    parameter: V,
    observer: ObserverPlugin<T>.() -> Unit
) =
    usecase.execute(parameter, this, observer)

fun <T, V : BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(
    usecase: BaseUseCase<T, V>,
    observer: ObserverPlugin<T>.() -> Unit
) =
    usecase.execute(this, observer)
