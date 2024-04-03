package com.example.upstoxassessment.ui.holdingscreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.upstoxassessment.R
import com.example.upstoxassessment.databinding.ActivityMainBinding
import com.example.upstoxassessment.ui.holdingscreens.adapter.StockHoldingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoldingActivity : AppCompatActivity() {
    private val stockHoldingViewModel: HoldingScreenViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private var stockHoldingAdapter: StockHoldingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
        setData()
    }

    private fun setData() {
        stockHoldingViewModel.getData()
        lifecycleScope.launch {
            stockHoldingViewModel.loadingState.collect {
                if (it)
                    binding.progressCircular.visibility = View.VISIBLE
                else
                    binding.progressCircular.visibility = View.GONE
            }
        }

        lifecycleScope.launch {
            stockHoldingViewModel.stockHoldingData.collect {
                if (it.isNotEmpty()) {
                    binding.tvNoDataFound.visibility = View.GONE
                    binding.rvStockHolder.visibility = View.VISIBLE
                    stockHoldingAdapter?.setData(it)
                } else {
                    binding.tvNoDataFound.visibility = View.VISIBLE
                    binding.rvStockHolder.visibility = View.GONE
                }
            }
        }

    }

    private fun setView() {
        stockHoldingAdapter = StockHoldingAdapter()
        binding.rvStockHolder.apply {
            layoutManager = LinearLayoutManager(this@HoldingActivity)
            adapter = stockHoldingAdapter
        }

    }


}