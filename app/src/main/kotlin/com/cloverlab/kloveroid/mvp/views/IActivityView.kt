package com.cloverlab.kloveroid.mvp.views

import com.trello.rxlifecycle3.android.ActivityEvent
import io.reactivex.Observable

/**
 * This specifies [IActivityView]
 *
 * @author  Jieyi Wu
 * @since   2017/09/25
 */
interface IActivityView {
    /**
     * Get a activity life cycle.
     */
    fun activityLifecycle(): Observable<ActivityEvent>
}
