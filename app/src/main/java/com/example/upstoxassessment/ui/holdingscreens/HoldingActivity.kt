package com.example.upstoxassessment.ui.holdingscreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upstoxassessment.R
import com.example.upstoxassessment.databinding.ActivityMainBinding
import com.example.upstoxassessment.ui.holdingscreens.adapter.StockHoldingAdapter
import com.example.upstoxassessment.ui.utils.setValue
import com.example.upstoxassessment.ui.utils.setValueWithAbsolutePercentage
import com.example.upstoxassessment.ui.utils.stringFormatter
import dagger.hilt.android.AndroidEntryPoint
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
        initListener()
    }

    private fun initListener() {
        binding.fixedCL.setOnClickListener {
            if (binding.clExpandableLayout.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.clExpandableLayout,
                    AutoTransition()
                )
                binding.clExpandableLayout.visibility = View.GONE
                binding.tvProfitAndLossLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_down_24,
                    0
                )
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.clExpandableLayout,
                    AutoTransition()
                )
                binding.clExpandableLayout.visibility = View.VISIBLE
                binding.tvProfitAndLossLabel.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.baseline_keyboard_arrow_up_24,
                    0
                )
            }
        }
    }

    private fun setData() {
        stockHoldingViewModel.getData()
        lifecycleScope.launch {
            stockHoldingViewModel.loadingState.collect {
                if (it) {
                    binding.progressCircular.visibility = View.VISIBLE
                } else {
                    binding.progressCircular.visibility = View.GONE
                }
            }
        }

        lifecycleScope.launch {
            stockHoldingViewModel.stockHoldingData.collect {
                if (it.isNotEmpty()) {
                    binding.rvStockHolder.visibility = View.VISIBLE
                    binding.fixedCL.visibility = View.VISIBLE
                    stockHoldingAdapter?.setData(it)
                }
            }
        }

        lifecycleScope.launch {
            stockHoldingViewModel.calculatedData.collect {
                binding.tvCurrentValue.text = it?.currentValue?.stringFormatter()
                binding.tvTotalInvestmentValue.text = it?.totalInvestment?.stringFormatter()
                binding.tvTodayPAndLValue.setValue(
                    it?.todaysPNL ?: 0.0,
                    context = this@HoldingActivity
                )
                binding.tvProfitAndLossValue.setValueWithAbsolutePercentage(
                    it?.totalPNL ?: 0.0,
                    context = this@HoldingActivity,
                    absolutePercentage = it?.absoluteReturn ?: 0.0
                )
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