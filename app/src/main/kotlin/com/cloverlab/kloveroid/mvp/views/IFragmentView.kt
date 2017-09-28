package com.cloverlab.kloveroid.mvp.views

import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Observable

/**
 * This specifies [].
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface IFragmentView {
    /**
     * Get a fragment life cycle.
     */
    fun fragmentLifecycle(): Observable<FragmentEvent>?
}
