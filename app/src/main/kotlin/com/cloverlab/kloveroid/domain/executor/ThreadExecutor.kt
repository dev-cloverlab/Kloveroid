package com.cloverlab.kloveroid.domain.executor

import com.cloverlab.kloveroid.domain.BaseUseCase
import java.util.concurrent.Executor

/**
 * Executor implementation can be based on different frameworks or techniques of asynchronous execution,
 * but every implementation will execute the [BaseUseCase] out of the UI thread.
 */
interface ThreadExecutor: Executor
