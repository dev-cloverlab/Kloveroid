package com.cloverlab.kloveroid.utilies

import com.cloverlab.kloveroid.usecases.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a [Scheduler] which will execute actions on the
 * Android UI thread.
 *
 * @author Jieyi Wu
 * @since 09/25/17
 */
@Singleton
class UIThread @Inject constructor(): PostExecutionThread {
    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}