package coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

interface AsyncManager : CoroutineScope {

    suspend fun <T> handleAsync(block: suspend CoroutineScope.() -> T): Deferred<T>
}