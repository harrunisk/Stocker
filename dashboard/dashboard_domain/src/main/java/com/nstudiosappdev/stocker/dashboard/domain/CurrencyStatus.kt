package com.nstudiosappdev.stocker.dashboard.domain

enum class CurrencyStatus(val value: String) {
    INCREASING("↑"),
    DECREASING("↓"),
    STABLE("=")
}