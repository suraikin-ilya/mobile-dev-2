package com.example.currencyconv.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.currencyconv.DependencyInjection
import com.example.currencyconv.data.db.model.HistoryModel
import com.example.currencyconv.databinding.FragmentExchangeBinding
import com.example.currencyconv.ui.view_model.ExchangeViewModel
import com.example.currencyconv.ui.view_model.factory.ExchangeViewModelFactory

class ExchangeFragment : Fragment() {
    
    private lateinit var binding: FragmentExchangeBinding
    private val args by navArgs<ExchangeFragmentArgs>()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeBinding.inflate(inflater, container, false)
        val repository = DependencyInjection.repository
        val viewModelFactory = ExchangeViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ExchangeViewModel::class.java]
        
        val crossCoef = args.value2 / args.value1
        
        binding.currency1.text = args.currency1
        binding.currency2.text = args.currency2
        binding.value1.setText("1.0")
        binding.value2.text = crossCoef.toString()
        
        binding.value1.addTextChangedListener {
            try {
                val value1 = binding.value1.text.toString().toFloat()
                val value2 = value1 * crossCoef
                binding.value2.text = value2.toString()
            } catch (e: Exception) {
                e.printStackTrace()
                binding.value2.text = "0"
            }
        }
        
        binding.exchangeButton.setOnClickListener {
            val value1 = binding.value1.text.toString()
            try {
                if (value1.isNotEmpty()) {
                    viewModel.saveTransaction(
                        HistoryModel(
                            id = 0,
                            currency1 = binding.currency1.text.toString(),
                            currency2 = binding.currency2.text.toString(),
                            value1 = binding.value1.text.toString().toFloat(),
                            value2 = binding.value2.text.toString().toFloat(),
                        )
                    )
                    Toast.makeText(requireContext(), "Транзакция совершена", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Ошибка в совершении транзакции",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    requireContext(),
                    "Ошибка в совершении транзакции",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        return binding.root
    }
}