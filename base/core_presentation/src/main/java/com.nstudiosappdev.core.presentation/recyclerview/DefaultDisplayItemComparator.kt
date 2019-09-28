package com.nstudiosappdev.core.presentation.recyclerview

class  DefaultDisplayItemComparator : DisplayItemComparator {

    override fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean {
        return oldItem == newItem
    }

}