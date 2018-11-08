package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.mvp.views.IView
import com.trello.rxlifecycle3.LifecycleProvider

/**
 * @author  Jieyi Wu
 * @since   2017/09/28
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
