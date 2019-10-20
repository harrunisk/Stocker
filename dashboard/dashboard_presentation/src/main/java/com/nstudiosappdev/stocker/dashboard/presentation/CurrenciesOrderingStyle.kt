package com.nstudiosappdev.stocker.dashboard.presentation

enum class OrderingStyle(val code: Int) {
    BY_NAME(0),
    BY_NAME_DESC(1),
    BY_BUYING_PRICE(2),
    BY_BUYING_PRICE_DESC(3),
    BY_SELLING_PRICE(4),
    BY_SELLING_PRICE_DESC(5),
    BY_DIFF(6),
    BY_DIFF_DESC(7)
}
