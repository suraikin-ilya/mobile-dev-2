package com.example.currencyconv.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconv.R
import com.example.currencyconv.data.db.model.CurrencyModel
import com.example.currencyconv.databinding.ItemCurrencyBinding

class CurrencyAdapter :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    
    var currencyList: MutableList<CurrencyModel> = mutableListOf()
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }
    
    override fun getItemCount(): Int = currencyList.size
    
    inner class CurrencyViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyModel: CurrencyModel) = binding.run {
            binding.currency.text = currencyModel.name
            if (currencyModel.chosen)
                itemView.setBackgroundResource(R.color.purple_500)
            else itemView.setBackgroundResource(R.color.item_background)


            itemView.setOnClickListener {
                val action = CurrencyFragmentDirections.actionCurrencyFragmentToExchangeFragment(
                    currency1 = currencyModel.name,
                    currency2 = currencyList[0].name,
                    value1 = currencyModel.value,
                    value2 = currencyList[0].value,
                )
                itemView.findNavController().navigate(action)
            }
            
            itemView.setOnLongClickListener {
                if (currencyList[0].chosen) {
                    currencyList[0].chosen = false
                    notifyDataSetChanged()
                } else {
                    currencyModel.chosen = true
                    currencyList.remove(currencyModel)
                    currencyList.add(0, currencyModel)
                    notifyDataSetChanged()
                }
                true
            }
        }
    }
}