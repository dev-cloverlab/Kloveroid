package com.cloverlab.kloveroid.data.executor

import com.cloverlab.kloveroid.domain.executor.ThreadExecutor
import dagger.internal.Preconditions
import java.util.concurrent.BlockingQueue
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
class JobExecutor @Inject constructor(): ThreadExecutor {
    private val workQueue: BlockingQueue<Runnable>
    private val threadPoolExecutor: ThreadPoolExecutor
    private val threadFactory: ThreadFactory

    init {
        workQueue = LinkedBlockingQueue()
        threadFactory = JobThreadFactory()
        threadPoolExecutor = ThreadPoolExecutor(INITIAL_POOL_SIZE,
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

    private class JobThreadFactory: ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, THREAD_NAME + counter++)
        }

        companion object {
            private val THREAD_NAME = "android_"
        }
    }

    companion object {
        private val INITIAL_POOL_SIZE = 3
        private val MAX_POOL_SIZE = 5

        // Sets the amount of time an idle thread waits before terminating
        private val KEEP_ALIVE_TIME = 10

        // Sets the Time Unit to seconds
        private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
    }
}
