package coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope

suspend fun CoroutineScope.tryCatch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
    handleCancellationExceptionManually: Boolean = false
) {
    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException || handleCancellationExceptionManually) {
            catchBlock(e)
        } else {
            throw e
        }
    }
}

suspend fun CoroutineScope.tryCatchFinally(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: suspend CoroutineScope.() -> Unit,
    handleCancellationExceptionManually: Boolean = false
) {
    var caughtThrowable: Throwable? = null

    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException || handleCancellationExceptionManually) {
            catchBlock(e)
        } else {
            caughtThrowable = e
        }
    } finally {
        if (caughtThrowable is CancellationException && !handleCancellationExceptionManually) {
            throw caughtThrowable
        } else {
            finallyBlock()
        }
    }
}

suspend fun CoroutineScope.tryFinally(
    tryBlock: suspend CoroutineScope.() -> Unit,
    finallyBlock: suspend CoroutineScope.() -> Unit,
    supressCancellationException: Boolean = false
) {
    var caughtThrowable: Throwable? = null

    try {
        tryBlock()
    } catch (e: CancellationException) {
        if (!supressCancellationException) {
            caughtThrowable = e
        }
    } finally {
        if (caughtThrowable is CancellationException && !supressCancellationException) {
            throw caughtThrowable
        } else {
            finallyBlock()
        }
    }
}