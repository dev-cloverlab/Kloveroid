package com.cloverlab.kloveroid.mvp.views

import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.Observable

/**
 * This specifies [IActivityView]
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
interface IActivityView {
    /**
     * Get a activity life cycle.
     */
    fun activityLifecycle(): Observable<ActivityEvent>
}
