package com.cloverlab.kloveroid.utilies.extension

import com.cloverlab.kloveroid.usecases.BaseUseCase
import com.devrapid.kotlinknifer.ObserverPlugin
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @author  jieyi
 * @since   10/17/17
 */
fun <T, V: BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(usecase: BaseUseCase<T, V>,
                                                                      parameter: V,
                                                                      observer: ObserverPlugin<T>.() -> Unit) =
    usecase.execute(parameter, this, observer)

fun <T, V: BaseUseCase.RequestValues, E> LifecycleProvider<E>.execute(usecase: BaseUseCase<T, V>,
                                                                      observer: ObserverPlugin<T>.() -> Unit) =
    usecase.execute(this, observer)