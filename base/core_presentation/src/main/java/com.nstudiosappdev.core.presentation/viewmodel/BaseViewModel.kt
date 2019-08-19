package com.nstudiosappdev.core.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nstudiosappdev.core.coroutines.CoroutineManager
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.livedata.SingleLiveData

abstract class BaseViewModel constructor(coroutineManager: CoroutineManager) : ViewModel(),
    CoroutineManager by coroutineManager {

    private val _errorLiveData = SingleLiveData<DataHolder.Fail>()

    val errorLiveData: MutableLiveData<DataHolder.Fail>
        get() = _errorLiveData

    override fun onCleared() {
        destroy()
        super.onCleared()
    }
}
