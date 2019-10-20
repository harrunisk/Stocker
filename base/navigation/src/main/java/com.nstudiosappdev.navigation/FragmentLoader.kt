package com.nstudiosappdev.navigation

import androidx.fragment.app.Fragment

internal fun String.loadFragmentOrReturnNull(): Fragment? =
    try {
        this.loadClassOrReturnNull<Fragment>()?.newInstance()
    } catch (e: ClassNotFoundException) {
        null
    }
