package com.nstudiosappdev.core.domain

import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred

interface Interactor {

    interface DeferredInteractor<params : Params, T : Any> : Interactor {
        suspend fun executeAsync(postParams: params): Deferred<DataHolder<T>>
    }

    interface DeferredRetrieveInteractor<T : Any> : Interactor {
        suspend fun executeAsync(): Deferred<DataHolder<T>>
    }

    interface SingleInteractor<params : Params, T : Any> : Interactor {
        fun execute(params: params): T?
    }

    interface SingleRetrieveInteractor<T : Any?> {
        fun execute(): T?
    }

    abstract class Params {
        // marker class
    }
}