package com.nstudiosappdev.core.presentation.base

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nstudiosappdev.core.presentation.Constants
import com.nstudiosappdev.core.presentation.R
import com.nstudiosappdev.core.presentation.navigation.UiNavigation

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    @StringRes
    open val titleRes = R.string.app_name

    @MenuRes
    open val menuRes = Constants.NO_RES

    open val uiNavigation = UiNavigation.BACK

    @IdRes
    open val toolbarRes = Constants.NO_RES

    @IdRes
    open val toolbarId = Constants.NO_RES


    open val shouldOnBackPressedWork = true

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        if (toolbarRes != Constants.NO_RES) {
            setToolbar(findViewById(toolbarRes))
        }
        initNavigation(uiNavigation)
        setScreenTitle(getString(titleRes))
        initToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(menuRes != Constants.NO_RES) {
            menuInflater.inflate(menuRes, menu)
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setScreenTitle(@StringRes titleRes: Int) {
        var title: String? = null
        try {
            title = getString(titleRes)
        } catch (e: Resources.NotFoundException) {
            //ignored
        }
        setScreenTitle(title)
    }

    fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
    }

    @SuppressLint("RestrictedApi")
    fun initNavigation(uiNavigation: UiNavigation) {
        when(uiNavigation) {
            UiNavigation.BACK -> supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
            UiNavigation.ROOT -> supportActionBar?.setDefaultDisplayHomeAsUpEnabled(false)
        }
    }

    fun setScreenTitle(title: String?) {
        supportActionBar?.title = title ?: getString(R.string.app_name)
    }

    override fun onBackPressed() {
        if (shouldOnBackPressedWork) {
            super.onBackPressed()
        }
    }

    private fun initToolBar() {
        if (toolbarId == Constants.NO_RES) return
        findViewById<Toolbar>(toolbarId)?.let {
            setToolbar(it)
        }
    }
}