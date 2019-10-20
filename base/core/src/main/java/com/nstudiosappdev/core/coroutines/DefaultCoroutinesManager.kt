package com.nstudiosappdev.core.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DefaultCoroutinesManager(override val coroutineContext: CoroutineContext) : CoroutineManager {

    private val coroutinesJobs: MutableList<Job> by lazy {
        mutableListOf<Job>()
    }

    override fun handleLaunch(execution: suspend CoroutineScope.() -> Unit) {
        val job: Job = launch { execution() }
        coroutinesJobs.add(job)
        job.invokeOnCompletion { coroutinesJobs.remove(job) }
    }

    @Synchronized
    override fun handleLaunch(
        execution: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(Throwable) -> Unit,
        handleCancellationExceptionManually: Boolean
    ) {
        launch { tryCatch(execution, error, handleCancellationExceptionManually) }
    }

    @Synchronized
    override fun handleLaunch(
        execution: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(Throwable) -> Unit,
        final: suspend CoroutineScope.() -> Unit,
        handleCancellationExceptionManually: Boolean
    ) {
        launch { tryCatchFinally(execution, error, final, handleCancellationExceptionManually) }
    }

    @Synchronized
    override fun handleLaunch(
        execution: suspend CoroutineScope.() -> Unit,
        final: suspend CoroutineScope.() -> Unit,
        supressCancellationException: Boolean
    ) {
        launch { tryFinally(execution, final, supressCancellationException) }
    }

    @Synchronized
    override fun cancelAllCoroutines() {
        val coroutineJobsSize = coroutinesJobs.size

        if (coroutineJobsSize > 0) {
            for (i in coroutineJobsSize - 1 downTo 0) {
                coroutinesJobs[i].cancel()
            }
        }
    }

    @Synchronized
    override fun destroy() {
        cancelAllCoroutines()
    }
}
