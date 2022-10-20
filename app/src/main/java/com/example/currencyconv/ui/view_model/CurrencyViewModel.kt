package com.example.currencyconv.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconv.data.db.model.CurrencyModel
import com.example.currencyconv.repository.Repository
import kotlinx.coroutines.launch

class CurrencyViewModel(private val repository: Repository) : ViewModel() {
    
    val currenciesLiveData: MutableLiveData<List<CurrencyModel>> = MutableLiveData()
    
    fun getCurrencies() {
        viewModelScope.launch {
            currenciesLiveData.postValue(repository.getCurrencies())
        }
    }
    
}