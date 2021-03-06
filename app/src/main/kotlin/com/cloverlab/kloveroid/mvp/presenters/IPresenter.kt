package com.cloverlab.kloveroid.mvp.presenters

import com.trello.rxlifecycle3.LifecycleProvider

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
interface IPresenter {
    /**
     * Initial method.
     */
    fun init()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onCreate() method.
     */
    fun <E> create(lifecycleProvider: LifecycleProvider<E>)

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onStart() method.
     */
    fun start()

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
     * onStop() method.
     */
    fun stop()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's (Activity or Fragment)
     * onDestroy() method.
     */
    fun destroy()
}
