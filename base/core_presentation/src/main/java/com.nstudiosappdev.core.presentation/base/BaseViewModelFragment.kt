package com.nstudiosappdev.core.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nstudiosappdev.core.presentation.viewmodel.BaseViewModel
import com.nstudiosappdev.navigation.navigation.DefaultNavigationController
import com.nstudiosappdev.navigation.navigation.NavigationController
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseViewModelFragment<VM : ViewModel> : BaseInjectionFragment() {

    @Inject
    protected lateinit var vmFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM

    private lateinit var navigationController: NavigationController

    abstract fun getModelClass(): Class<VM>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationController = DefaultNavigationController(WeakReference(activity!!))
        viewModel = ViewModelProviders.of(this, vmFactory).get(getModelClass())
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (viewModel is BaseViewModel) {
            (viewModel as BaseViewModel).errorLiveData.observe(this, Observer {
                onError(it.e)
            })
        }
    }
}
