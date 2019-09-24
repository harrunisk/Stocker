package com.nstudiosappdev.core.data.db

class Db private constructor() {

    object Config {
        const val DB_NAME = "stocker"
        const val DB_VERSION = 3
        const val BANK_NAME = "bankName"
        const val BUY_PRICE = "buyPrice"
        const val BUY_STATUS = "buyStatus"
        const val SELL_PRICE = "sellPrice"
        const val SELL_STATUS = "sellStatus"
        const val CURRENCY_TYPE = "currencyType"
        const val CREATE_DATE = "create_date"
        const val UPDATE_DATE = "update_date"
    }

    object TABLES {

        object CURRENCIES {
            const val NAME = "currencies"

            object COLUMNS {
                const val BANK_NAME = Config.BANK_NAME
                const val BUY_PRICE = Config.BUY_PRICE
                const val BUY_STATUS = Config.BUY_STATUS
                const val SELL_PRICE = Config.SELL_PRICE
                const val SELL_STATUS = Config.SELL_STATUS
                const val CURRENCY_TYPE = Config.CURRENCY_TYPE
                const val CREATE_DATE = Config.CREATE_DATE
                const val UPDATE_DATE = Config.UPDATE_DATE
            }
        }
    }
}