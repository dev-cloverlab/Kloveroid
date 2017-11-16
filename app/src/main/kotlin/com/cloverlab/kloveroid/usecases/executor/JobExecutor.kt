package com.cloverlab.kloveroid.usecases.executor

import dagger.internal.Preconditions
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Decorated [ThreadPoolExecutor]
 */
@Singleton
class JobExecutor @Inject constructor() : ThreadExecutor {
    companion object {
        private const val INITIAL_POOL_SIZE = 3
        private const val MAX_POOL_SIZE = 5
        // Sets the amount of time an idle thread waits before terminating
        private const val KEEP_ALIVE_TIME = 10
        // Sets the Time Unit to seconds
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    }

    private val workQueue by lazy { LinkedBlockingQueue<Runnable>() }
    private val threadFactory by lazy { JobThreadFactory() }
    private val threadPoolExecutor by lazy {
        ThreadPoolExecutor(INITIAL_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME.toLong(),
            KEEP_ALIVE_TIME_UNIT,
            workQueue,
            threadFactory)
    }

    override fun execute(runnable: Runnable) {
        Preconditions.checkNotNull(runnable)

        threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        companion object {
            private val THREAD_NAME = "android_"
        }

        private var counter = 0

        override fun newThread(runnable: Runnable): Thread = Thread(runnable, THREAD_NAME + counter++)
    }
}
