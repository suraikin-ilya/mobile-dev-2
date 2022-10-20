package com.example.currencyconv.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconv.data.db.model.HistoryModel
import com.example.currencyconv.databinding.ItemHistoryBinding
import java.time.Instant
import java.time.ZoneId

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    
    var historyList: MutableList<HistoryModel> = mutableListOf()
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }
    
    override fun getItemCount(): Int = historyList.size
    
    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(historyModel: HistoryModel) = binding.run {
            binding.currency1.text = historyModel.currency1
            binding.currency2.text = historyModel.currency2
            binding.value1.text = historyModel.value1.toString()
            binding.value2.text = historyModel.value2.toString()
            val dateLocal =
                Instant.ofEpochMilli(historyModel.date).atZone(ZoneId.systemDefault()).toLocalDate()
            binding.date.text = dateLocal.toString()
        }
    }
}
