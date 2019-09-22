package com.nstudiosappdev.core.presentation.base

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.nstudiosappdev.core.error.Error
import com.nstudiosappdev.core.presentation.Constants
import com.nstudiosappdev.core.presentation.navigation.UiNavigation

abstract class BaseFragment : Fragment(), BaseView {

    @LayoutRes
    protected abstract fun getLayoutRes(): Int

    @MenuRes
    open val menuRes = Constants.NO_RES

    @StringRes
    open val titleRes = Constants.NO_RES

    @IdRes
    open val toolbarId = Constants.NO_RES

    open val uiNavigation = UiNavigation.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(menuRes != Constants.NO_RES)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (menuRes != Constants.NO_RES) {
            menu.clear()
            inflater.inflate(menuRes, menu)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutRes(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolBar()
        if (titleRes != Constants.NO_RES) {
            setActivityTitle(getString(titleRes))
        }
        if (uiNavigation != UiNavigation.NONE) {
            setupNavigation()
        }
    }

    override fun onError(e: Error) {
        try {
            // Alert Dialog or Toast message show error,
            val errorMessage = getErrorMessage(e)
            Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show()
        } catch (e: WindowManager.BadTokenException) {
            // no-op
        }
    }


    protected fun setActivityTitle(title: String) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).setScreenTitle(title)
        }
    }

    fun getApplication() = activity?.application

    fun getApplicationContext() = getApplication()?.applicationContext

    open fun initView() {
        // can be overridden
    }

    private fun getErrorMessage(e: Error): String {
        return when (e) {
            is Error.ApiError ->{
                e.message
            }
            is Error.ApiErrors -> {
                val message = StringBuilder()
                for (apiError in e.errors) {
                    message.append(getErrorMessage(apiError))
                    message.append("\n")
                }
                message.toString()
            }
            is Error.EmptyCacheResult -> {
                "Empty Cache"
            }
            is Error.InvalidResponseError ->{
                "Invalid Response"
            }
            is Error.BusinessError ->{
                "Business Error -> error code ${e.code} error message ${e.message}"
            }
            is Error.AuthenticationError ->{
                "Your Authentication is fail"
            }
            else -> "UnKnown Error"
        }
    }

    private fun initToolBar() {
        if (toolbarId == Constants.NO_RES) return
        view?.findViewById<Toolbar>(toolbarId)?.let {
            if (activity is BaseActivity) {
                (activity as BaseActivity).setToolbar(it)
            }
        }
    }

    private fun setupNavigation() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).initNavigation(uiNavigation)
        }
    }
}