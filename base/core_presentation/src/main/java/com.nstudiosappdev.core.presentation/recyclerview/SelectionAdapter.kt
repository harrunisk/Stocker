package com.nstudiosappdev.core.presentation.recyclerview

interface SelectionAdapter {
    fun select(pos: Int)
    fun clear()
    fun getSelectedItemCount(): Int
    fun getSelectedItems(): List<DisplayItem>
}
