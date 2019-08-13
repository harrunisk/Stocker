package coroutines

import kotlin.coroutines.CoroutineContext

class DefaultAsyncManager(
    override val coroutineContext: CoroutineContext
) : AsyncManager{
}