/*
 * Binds ViewHolder and DisplayItem
 */

package com.nstudiosappdev.core.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView

interface ViewHolderBinder {
    fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem)
}

