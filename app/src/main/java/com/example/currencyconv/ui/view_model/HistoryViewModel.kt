package com.example.currencyconv.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconv.data.db.model.HistoryModel
import com.example.currencyconv.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository) : ViewModel() {
    
    val historyListLiveData: MutableLiveData<List<HistoryModel>> = MutableLiveData()
    
    fun readHistory() {
        viewModelScope.launch(Dispatchers.IO) {
                        historyListLiveData.postValue(repository.readAllHistory())
                    }
            }
        }