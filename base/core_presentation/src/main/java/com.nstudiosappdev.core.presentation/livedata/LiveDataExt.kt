package com.nstudiosappdev.core.presentation.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseView

inline fun <T : Any> LiveData<DataHolder<T>>.observeApi(
    lifecycleOwner: LifecycleOwner,
    crossinline body: (DataHolder<T>?) -> Unit
) {
    observe(lifecycleOwner, Observer { bean: DataHolder<T>? ->
        if (bean is DataHolder.Fail) {
            if (lifecycleOwner is BaseView) {
                lifecycleOwner.onError(bean.e)
            }
        }
        body(bean)
    })
}