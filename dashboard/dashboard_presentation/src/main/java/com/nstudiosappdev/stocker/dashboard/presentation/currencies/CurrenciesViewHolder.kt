package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nstudiosappdev.core.presentation.extensions.adjustSensitivityGiveString
import com.nstudiosappdev.core.presentation.extensions.adjustSensitivityGiveFloat
import com.nstudiosappdev.core.presentation.extensions.createCustomAlertDialog
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.ViewHolder
import com.nstudiosappdev.core.presentation.recyclerview.ViewHolderBinder
import com.nstudiosappdev.core.presentation.recyclerview.ViewHolderFactory
import com.nstudiosappdev.stocker.dashboard.domain.CurrencyStatus
import com.nstudiosappdev.stocker.presentation.R
import javax.inject.Inject
import kotlin.math.abs

class CurrenciesViewHolder private constructor(itemView: View) : ViewHolder(itemView) {

    private val textViewBankName: TextView = itemView.findViewById(R.id.textViewBankName)
    private val textViewBuyingPrice: TextView = itemView.findViewById(R.id.textViewBuyingPrice)
    private val textViewSellingPrice: TextView = itemView.findViewById(R.id.textViewSellingPrice)
    private val textViewDiff: TextView = itemView.findViewById(R.id.textViewDiff)

    lateinit var buyingPrice: String
    lateinit var sellingPrice: String

    private fun bind(item: CurrenciesViewEntity) {

        when (item.buyStatus) {
            CurrencyStatus.DECREASING.value -> {
                buyingPrice = item.buyPrice?.adjustSensitivityGiveString(4) + CurrencyStatus.DECREASING.value
                textViewBuyingPrice.text = buyingPrice
                textViewBuyingPrice.setTextColor(Color.RED)
            }
            CurrencyStatus.INCREASING.value -> {
                buyingPrice = item.buyPrice?.adjustSensitivityGiveString(4) + CurrencyStatus.INCREASING.value
                textViewBuyingPrice.text = buyingPrice
                textViewBuyingPrice.setTextColor(Color.GREEN)
            }
            else -> {
                textViewBuyingPrice.text = item.buyPrice?.adjustSensitivityGiveString(4)
            }
        }

        when (item.sellStatus){
            CurrencyStatus.DECREASING.value -> {
                sellingPrice = item.sellPrice?.adjustSensitivityGiveString(4) + CurrencyStatus.DECREASING.value
                textViewBuyingPrice.text = sellingPrice
                textViewSellingPrice.setTextColor(Color.RED)
            }
            CurrencyStatus.INCREASING.value -> {
                sellingPrice = item.sellPrice?.adjustSensitivityGiveString(4) + CurrencyStatus.INCREASING.value
                textViewSellingPrice.text = sellingPrice
                textViewSellingPrice.setTextColor(Color.GREEN)
            }
            else -> {
                textViewSellingPrice.text = item.sellPrice?.adjustSensitivityGiveString(4)
            }
        }

        textViewBankName.text = item.bankName
        textViewDiff.text = abs(
            item.sellPrice!!.adjustSensitivityGiveFloat(4) - item.buyPrice!!.adjustSensitivityGiveFloat(4)
        ).toString().adjustSensitivityGiveString(3)

        itemView.setOnClickListener {
            itemClickListener?.invoke(item)
            itemView.context.createCustomAlertDialog(
                message = item.bankName + " " + itemView.context.getString(R.string.message),
                title = itemView.context.getString(R.string.title),
                positiveButtonText = itemView.context.getString(R.string.add),
                negativeButtonText = itemView.context.getString(R.string.cancel),
                imageView = null
            ).show()
       }

        itemView.setOnLongClickListener {
            itemLongClickListener?.invoke(item)
            true
        }
    }

    internal class CurrenciesViewHolderFactory @Inject constructor() : ViewHolderFactory {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            CurrenciesViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_currency,
                    parent,
                    false
                )
            )
    }

    internal class CurrenciesViewHolderBinder @Inject constructor() : ViewHolderBinder {

        override fun bind(holder: RecyclerView.ViewHolder, item: DisplayItem) {
            (holder as CurrenciesViewHolder).bind(item as CurrenciesViewEntity)
        }
    }
}