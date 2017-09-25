package com.cloverlab.kloveroid.mvp.presenters

import com.cloverlab.kloveroid.mvp.views.IView

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */

interface IPresenter<in V: IView> {
    /**
     * Set a viewable.
     *
     * @param view [IView]
     */
    fun setView(view: V)

    /**
     * Initial method.
     */
    fun init()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onResume() method.
     */
    fun resume()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onPause() method.
     */
    fun pause()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onDestroy() method.
     */
    fun destroy()
}