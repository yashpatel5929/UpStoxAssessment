package com.example.upstoxassessment.ui.holdingscreens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.upstoxassessment.R
import com.example.upstoxassessment.databinding.ItemListStockHoldingBinding
import com.example.upstoxassessment.domain.model.StockHoldingData
import com.example.upstoxassessment.ui.utils.setValue

class StockHoldingAdapter : RecyclerView.Adapter<StockHoldingAdapter.ViewHolder>() {
    private val stockDataList: MutableList<StockHoldingData> = mutableListOf()

    inner class ViewHolder(val binding: ItemListStockHoldingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StockHoldingData) {
            binding.tvSymbol.text = item.symbol
            binding.tvNetQuantitValue.text = item.quantity?.toString()
            binding.tvLtpValue.text =
                itemView.context.getString(R.string.ltp_value_string, item.ltp.toString())
            binding.tvPAndLValue.setValue(item.totalPAndL ?: 0.0, itemView.context)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StockHoldingAdapter.ViewHolder {
        val view =
            ItemListStockHoldingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockHoldingAdapter.ViewHolder, position: Int) {
        val item = stockDataList[position]
        holder.bind(item)
    }


    override fun getItemCount(): Int {
        return stockDataList.size
    }

    fun setData(data: List<StockHoldingData>) {
        stockDataList.clear()
        stockDataList.addAll(data)
        notifyDataSetChanged()
    }
}