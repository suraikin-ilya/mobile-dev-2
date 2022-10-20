package com.example.currencyconv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconv.DependencyInjection
import com.example.currencyconv.databinding.FragmentCurrencyBinding
import com.example.currencyconv.ui.view_model.CurrencyViewModel
import com.example.currencyconv.ui.view_model.factory.CurrencyViewModelFactory

class CurrencyFragment : Fragment() {
    
    private lateinit var binding: FragmentCurrencyBinding
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        val repository = DependencyInjection.repository
        val viewModelFactory = CurrencyViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[CurrencyViewModel::class.java]
        viewModel.getCurrencies()
        
        val adapter = CurrencyAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        
        viewModel.currenciesLiveData.observe(viewLifecycleOwner) {
            adapter.currencyList = it.toMutableList()
            adapter.notifyDataSetChanged()
        }
        
        return binding.root
    }
}