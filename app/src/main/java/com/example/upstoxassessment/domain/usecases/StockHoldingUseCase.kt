package com.example.upstoxassessment.domain.usecases

import com.example.upstoxassessment.domain.model.CalculatedData
import com.example.upstoxassessment.domain.model.StockHoldingData
import com.example.upstoxassessment.domain.repositories.StockHoldingRepositories
import com.example.upstoxassessment.ui.utils.Resource

class StockHoldingUseCase(
    private val stockHoldingRepositories: StockHoldingRepositories
) {
    suspend operator fun invoke(): Resource<List<StockHoldingData>> {
        val result = stockHoldingRepositories.getStockHoldingData()
        return result
    }

    fun calculateData(stockHoldingData: List<StockHoldingData>): CalculatedData {
        val currentValue = stockHoldingData.sumOf {
            it.ltp?.times(it.quantity ?: 0) ?: 0.0
        }

        val totalInvesment = stockHoldingData.sumOf {
            it.avgPrice?.times(it.quantity ?: 0) ?: 0.0
        }

        val totalPNL = currentValue - totalInvesment
        val todayPNL = stockHoldingData.sumOf {
            ((it.close?.minus(it.ltp ?: 0.0))?.times(it.quantity ?: 0)) ?: 0.0
        }

        val absolutePercentage = (totalPNL / totalInvesment) * 100

        return CalculatedData(
            currentValue = currentValue,
            totalInvestment = totalInvesment,
            totalPNL = totalPNL,
            todaysPNL = todayPNL,
            absoluteReturn = absolutePercentage
        )
    }
}