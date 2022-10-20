package com.example.currencyconv.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencyconv.DependencyInjection
import com.example.currencyconv.databinding.FragmentHistoryBinding
import com.example.currencyconv.ui.view_model.HistoryViewModel
import com.example.currencyconv.ui.view_model.factory.HistoryViewModelFactory

@Suppress("DEPRECATION")
class HistoryFragment : Fragment() {
    
    private lateinit var binding: FragmentHistoryBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val repository = DependencyInjection.repository
        val viewModelFactory = HistoryViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]
        
        val adapter = HistoryAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        
        viewModel.readHistory()
        viewModel.historyListLiveData.observe(viewLifecycleOwner) {
            adapter.historyList = it.toMutableList()
            adapter.notifyDataSetChanged()
        }
        
        return binding.root
    }

}