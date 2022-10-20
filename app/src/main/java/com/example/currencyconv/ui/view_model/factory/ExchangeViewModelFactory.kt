package com.example.currencyconv.ui.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconv.repository.Repository
import com.example.currencyconv.ui.view_model.ExchangeViewModel

class ExchangeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeViewModel::class.java))
            return ExchangeViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}