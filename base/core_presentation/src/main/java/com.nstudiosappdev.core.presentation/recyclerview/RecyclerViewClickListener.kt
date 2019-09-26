package com.nstudiosappdev.core.presentation.recyclerview

import android.view.View
import com.nstudiosappdev.core.presentation.entity.ViewEntity

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View, item: ViewEntity)
}