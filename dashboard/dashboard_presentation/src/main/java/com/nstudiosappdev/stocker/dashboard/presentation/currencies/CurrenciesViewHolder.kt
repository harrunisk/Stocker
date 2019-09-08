package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.ViewHolder
import com.nstudiosappdev.core.presentation.recyclerview.ViewHolderBinder
import com.nstudiosappdev.core.presentation.recyclerview.ViewHolderFactory
import com.nstudiosappdev.stocker.presentation.R
import javax.inject.Inject

class CurrenciesViewHolder private constructor(itemView: View) : ViewHolder(itemView) {

    private val textViewBankName: TextView = itemView.findViewById(R.id.textViewBankName)
    private val textViewBuyingPrice: TextView = itemView.findViewById(R.id.textViewBuyingPrice)
    private val textViewSellingPrice: TextView = itemView.findViewById(R.id.textViewSellingPrice)

    private fun bind(item: CurrenciesViewEntity) {
        textViewBankName.text = item.bankName
        textViewBuyingPrice.text = item.buyPrice
        textViewSellingPrice.text = item.sellPrice

        itemView.setOnClickListener {
            itemClickListener?.invoke(item)
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