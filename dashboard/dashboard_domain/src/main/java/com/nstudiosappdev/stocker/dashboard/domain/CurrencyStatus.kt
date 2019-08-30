package com.nstudiosappdev.stocker.dashboard.domain

enum class CurrencyStatus(val sign: String) {
    INCREASING("↑"),
    DECREASING("↓"),
    STABLE("=")
}