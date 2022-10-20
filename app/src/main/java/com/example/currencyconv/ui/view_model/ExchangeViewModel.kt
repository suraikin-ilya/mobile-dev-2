package com.example.currencyconv.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconv.data.db.model.HistoryModel
import com.example.currencyconv.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExchangeViewModel(private val repository: Repository) : ViewModel() {
    
    fun saveTransaction(historyModel: HistoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveTransaction(historyModel)
        }
    }
}