/**
 * Creates ViewHolder
 */

package com.nstudiosappdev.core.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ViewHolderFactory {
    fun createViewHolder(parent: ViewGroup, recyclerViewClickListener: RecyclerViewClickListener): RecyclerView.ViewHolder
}