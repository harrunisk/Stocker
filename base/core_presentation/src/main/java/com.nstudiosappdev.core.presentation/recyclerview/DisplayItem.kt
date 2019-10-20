/*
 * Ui Objects which'll be shown with Common RecyclerView Adapter must implement this interface.
 * Type represents item typeç
 * Model is base model of object.
 */
package com.nstudiosappdev.core.presentation.recyclerview

interface DisplayItem {
    fun type(): Int
}
