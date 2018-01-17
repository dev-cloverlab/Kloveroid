package com.cloverlab.kloveroid.utilies.extension

import com.cloverlab.kloveroid.usecases.BaseUseCase
import com.devrapid.kotlinknifer.ObserverPlugin
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable

/**
 * @author  jieyi
 * @since   10/17/17
 */
fun <T, V : BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(usecase: BaseUseCase<T, V>,
                                                                       parameter: V,
                                                                       block: (Observable<T>.() -> Observable<T>)? = null,
                                                                       observer: ObserverPlugin<T>.() -> Unit) =
    usecase.execute(parameter, this, block, observer)

fun <T, V : BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(usecase: BaseUseCase<T, V>,
                                                                       block: (Observable<T>.() -> Observable<T>)? = null,
                                                                       observer: ObserverPlugin<T>.() -> Unit) =
    usecase.execute(this, block, observer)