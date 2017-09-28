package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.mvp.views.IView

/**
 * @author  jieyi
 * @since   9/28/17
 */
abstract class BasePresenter<V: IView>: IPresenter {
    open lateinit var view: V

    override fun init() {}

    override fun create() {}

    override fun start() {}

    override fun resume() {}

    override fun pause() {}

    override fun stop() {}

    override fun destroy() {}
}