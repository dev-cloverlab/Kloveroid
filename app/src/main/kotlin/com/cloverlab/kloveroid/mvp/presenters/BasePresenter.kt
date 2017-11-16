package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.mvp.views.IView
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @author  jieyi
 * @since   9/28/17
 */
abstract class BasePresenter<V : IView> : IPresenter {
    open lateinit var view: V
    protected lateinit var lifecycleProvider: LifecycleProvider<*>

    override fun <E> create(lifecycleProvider: LifecycleProvider<E>) {
        this.lifecycleProvider = lifecycleProvider
    }

    override fun init() {}

    override fun start() {}

    override fun resume() {}

    override fun pause() {}

    override fun stop() {}

    override fun destroy() {}
}