/*
 * Compares display items
 */

package com.nstudiosappdev.core.presentation.recyclerview

interface DisplayItemComparator {
    fun areItemsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean

    fun areContentsSame(oldItem: DisplayItem, newItem: DisplayItem): Boolean
}
